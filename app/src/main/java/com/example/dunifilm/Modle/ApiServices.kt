package com.example.dunifilm.Modle

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    @GET("genres")
    fun getGenresList(): Call<List<Genres>>

    @GET("genres/{genre_id}/movies")
    fun getMovies(
        @Path("genre_id") genreId: Int,
        @Query("page") page: Int = 1
    ): Call<Movie>

    @GET("movies")
    fun searchMovies(
        @Query("q") name: String,
        @Query("page") page: Int = 1
    ): Call<Movie>

    @GET("movies/{movie_id}")
    fun getMovieInfo(@Path("movie_id") movieId: Int): Call<Movie_Info>
}