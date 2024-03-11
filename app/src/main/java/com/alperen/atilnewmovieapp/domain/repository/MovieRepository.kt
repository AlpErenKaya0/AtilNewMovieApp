package com.alperen.atilnewmovieapp.domain.repository

import com.alperen.atilnewmovieapp.data.remote.dto.MovieDetailDto
import com.alperen.atilnewmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {
    suspend fun getMovies(search:String):MoviesDto
    suspend fun getMovieDetail(imdbId:String): MovieDetailDto
}