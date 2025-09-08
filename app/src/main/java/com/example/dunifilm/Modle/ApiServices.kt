package com.example.dunifilm.Modle

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {


    @GET("genres")
    fun getGenresList(): Call<List<Genres>>


}