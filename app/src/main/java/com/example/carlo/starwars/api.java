package com.example.carlo.starwars;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api     {
    String BASE_URL = "https://swapi.co/api/";
    @GET("people")
    Call<List<hero>> getHeroes();
}
