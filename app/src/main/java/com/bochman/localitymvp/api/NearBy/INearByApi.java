package com.bochman.localitymvp.api.NearBy;

import com.bochman.localitymvp.BuildConfig;
import com.bochman.localitymvp.api.NearBy.pojos.PlaceSearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INearByApi {

    String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    String API_KEY = BuildConfig.MY_API_KEY;

    @GET("nearbysearch/json")
    Call<PlaceSearchResults> getDetails(
            @Query("location") String loc,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("keyword") String keyword,
            @Query("key") String key
    );
}
