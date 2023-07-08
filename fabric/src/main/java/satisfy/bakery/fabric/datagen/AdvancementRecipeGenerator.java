package satisfy.bakery.fabric.datagen;

import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdvancementRecipeGenerator {

    public static String FOLDER = "/Users/marco/Desktop/Neuer Ordner/";

    public static void main(String[] args) {
        List<String> putRecipesHere = List.of(

                "minecraft:tray/bread_crate"

        );

        for (String s : putRecipesHere) {
            List<String> list1 = Arrays.stream(s.split("/")).toList();

            if (list1.size() < 2) {
                System.out.println("False entry: " + s);
                continue;
            }

            write(list1.get(0), list1.get(1));
        }
    }


    public static void write(String condition, String recipe) {
        String fullRecipe = "bakery:" + recipe;
        String fullCondition = "bakery:" + condition;

        if (condition.contains(":")) {
            fullCondition = condition;
            condition = Arrays.stream(condition.split(":")).toList().get(1);
        }

        if (recipe.contains(":")) {
            fullRecipe = condition;
            recipe = Arrays.stream(recipe.split(":")).toList().get(1);
        }


        // Writing
        try (FileWriter fileWriter = new FileWriter(FOLDER + recipe + ".json"); JsonWriter jsonWriter = new JsonWriter(fileWriter)) {
            jsonWriter.setIndent("  ");
            jsonWriter.beginObject()
                    .name("parent").value("minecraft:recipes/root")
                    .name("rewards").beginObject().name("recipes").beginArray().value(fullRecipe).endArray().endObject()
                    .name("criteria").beginObject().name("has_" + condition).beginObject().name("trigger").value("minecraft:inventory_changed").name("conditions").beginObject().name("items").beginArray().beginObject().name("items").beginArray().value(fullCondition).endArray().endObject().endArray().endObject().endObject().name("has_the_recipe").beginObject().name("trigger").value("minecraft:recipe_unlocked").name("conditions").beginObject().name("recipe").value(fullRecipe).endObject().endObject().endObject()
                    .name("requirements").beginArray().beginArray().value("has_" + condition).value("has_the_recipe").endArray().endArray()

                    .endObject();
        } catch (IOException e) {
            System.out.printf("[Bakery] Couldn't write recipe to " + FOLDER + recipe);
            e.printStackTrace();
        }
    }
}
