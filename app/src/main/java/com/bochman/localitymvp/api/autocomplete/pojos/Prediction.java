package com.bochman.localitymvp.api.autocomplete.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prediction
{
    String description;
    String id;

    @SerializedName("place_id")
    String placeID;

    String reference;
    List<String> types;
}
