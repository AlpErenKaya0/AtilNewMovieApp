package com.alperen.atilnewmovieapp.data.remote

import com.alperen.atilnewmovieapp.data.remote.dto.MovieDetailDto
import com.alperen.atilnewmovieapp.data.remote.dto.MoviesDto
import com.alperen.atilnewmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apikey:String = API_KEY
    ) : MoviesDto
    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apikey:String = API_KEY
    ) : MovieDetailDto
}