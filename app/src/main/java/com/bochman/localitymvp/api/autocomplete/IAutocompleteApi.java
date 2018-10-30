package com.bochman.localitymvp.api.autocomplete;

import com.bochman.localitymvp.BuildConfig;
import com.bochman.localitymvp.api.autocomplete.pojos.Predictions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAutocompleteApi {

    String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    String API_KEY = BuildConfig.MY_API_KEY;

    @GET("autocomplete/json")
    Call<Predictions> getNearbyResults(
            @Query("types") String types,
            @Query("input") String input,
            @Query("location") String location,
            @Query("radius") Integer radius,
            @Query("key") String key
    );

    //        ImageView ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
    //        String url = "https://maps.googleapis.com/maps/api/place/photo" +
    //                "?maxwidth=400" +
    //                "&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU" +
    //                "&key=AIzaSyAfDHQhe4fmz5FAitOnTjrOFPesb88GXFE";
    //
    //    Glide.with(this)
    //            .load(url)
    //        .into(ivPhoto);
}





