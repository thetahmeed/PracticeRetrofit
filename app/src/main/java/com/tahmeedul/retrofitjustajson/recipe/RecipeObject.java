package com.tahmeedul.retrofitjustajson.recipe;

import java.util.List;

public class RecipeObject {

    String title;
    List<RObject2> analyzedInstructions;

    public String getTitle() {
        return title;
    }

    public List<RObject2> getAnalyzedInstructions() {
        return analyzedInstructions;
    }
}
