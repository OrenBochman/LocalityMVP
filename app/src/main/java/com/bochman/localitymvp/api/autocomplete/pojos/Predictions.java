package com.bochman.localitymvp.api.autocomplete.pojos;

import com.bochman.localitymvp.api.autocomplete.pojos.Prediction;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Predictions
{
    String status;

    @SerializedName("Predictions")
    List<Prediction> predictionList;
}

