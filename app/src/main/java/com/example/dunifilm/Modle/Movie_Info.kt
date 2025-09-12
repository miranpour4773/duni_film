package com.example.dunifilm.Modle


import com.google.gson.annotations.SerializedName

data class Movie_Info(
    @SerializedName("actors")
    val actors: String?,
    @SerializedName("awards")
    val awards: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("imdb_rating")
    val imdbRating: String,
    @SerializedName("imdb_votes")
    val imdbVotes: String,
    @SerializedName("metascore")
    val metascore: String,
    @SerializedName("plot")
    val plot: String?,
    @SerializedName("poster")
    val poster: String?,
    @SerializedName("rated")
    val rated: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("runtime")
    val runtime: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("writer")
    val writer: String?,
    @SerializedName("year")
    val year: String?
)