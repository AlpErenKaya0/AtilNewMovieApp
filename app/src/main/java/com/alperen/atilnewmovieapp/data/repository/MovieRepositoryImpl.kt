package com.alperen.atilnewmovieapp.data.repository

import com.alperen.atilnewmovieapp.data.remote.MovieAPI
import com.alperen.atilnewmovieapp.data.remote.dto.MovieDetailDto
import com.alperen.atilnewmovieapp.data.remote.dto.MoviesDto
import com.alperen.atilnewmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieAPI):MovieRepository{
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId= imdbId)
    }
}