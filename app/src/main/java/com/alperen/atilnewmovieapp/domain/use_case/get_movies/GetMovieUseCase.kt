package com.alperen.atilnewmovieapp.domain.use_case.get_movies

import android.net.http.HttpException
import com.alperen.atilnewmovieapp.data.remote.dto.toMovieList
import com.alperen.atilnewmovieapp.domain.model.Movie
import com.alperen.atilnewmovieapp.domain.repository.MovieRepository
import com.alperen.atilnewmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository ) {
    //operator fun invoke da kullanılabilir
    fun executeGetMovies(search: String): Flow <Resource<List<Movie>>> = flow {
      try {
        emit(Resource.Loading())
          val movieList = repository.getMovies(search)
          if(movieList.Response.equals("True")) {
            emit(Resource.Success(movieList.toMovieList()))
          } else {
             emit(Resource.Error(message="No movie found!"))
          }
      }
      catch (e:IOError){
        emit(Resource.Error(message = "No internet connection."))
      }
        catch (e:retrofit2.HttpException) {
            emit(Resource.Error(message = e.localizedMessage?:"Error"))
        }
    }


}