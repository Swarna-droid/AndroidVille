package com.tripathi.swarna.android.themoviedatabaseapp.api;


import com.tripathi.swarna.android.themoviedatabaseapp.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("search/movie")
    Call<SearchResult> getListOfMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") int pageIndex
    );

}
