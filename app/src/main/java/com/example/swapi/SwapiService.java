package com.example.swapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SwapiService {
    @GET("people")
    Call<CharacterResponse> getCharacters(@Query("search") String query);

    @GET
    Call<CharacterResponse> getCharactersFromUrl(@Url String url);
}
