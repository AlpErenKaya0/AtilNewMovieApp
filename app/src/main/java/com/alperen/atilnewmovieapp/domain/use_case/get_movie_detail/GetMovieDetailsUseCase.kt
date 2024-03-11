package com.alperen.atilnewmovieapp.domain.use_case.get_movie_detail

import com.alperen.atilnewmovieapp.data.remote.dto.toMovieDetail
import com.alperen.atilnewmovieapp.data.remote.dto.toMovieList
import com.alperen.atilnewmovieapp.domain.model.Movie
import com.alperen.atilnewmovieapp.domain.model.MovieDetail
import com.alperen.atilnewmovieapp.domain.repository.MovieRepository
import com.alperen.atilnewmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        }
        catch (e: IOError){
            emit(Resource.Error(message = "No internet connection."))
        }
        catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?:"Error"))
        }
    }
}