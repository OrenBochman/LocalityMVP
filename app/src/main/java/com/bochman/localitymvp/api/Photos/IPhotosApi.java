package com.bochman.localitymvp.api.Photos;

import com.bochman.localitymvp.BuildConfig;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPhotosApi {

    String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    String API_KEY = BuildConfig.MY_API_KEY;

    @GET("photoreference/json")
    Call<ResponseBody> getSinglePhoto(
            @Query("maxwidth") Integer maxwidth,
            @Query("photoreference") String photoreference,
            @Query("key") String key);

}
