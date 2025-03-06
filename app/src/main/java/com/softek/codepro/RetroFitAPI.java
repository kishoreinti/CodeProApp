package com.softek.codepro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroFitAPI {

    @GET("top-headlines")
    Call<NewsModal> getTopHeadlines(@Query("country") String country,
                                       @Query("category") String category,
                                       @Query("apikey") String apiKey);
}
