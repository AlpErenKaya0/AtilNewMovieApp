package com.alperen.atilnewmovieapp.presentation.movies.views

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperen.atilnewmovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.alperen.atilnewmovieapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMovieUseCase:GetMovieUseCase
): ViewModel() {

    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state: State<MoviesState> = _state
    private var job : Job?= null
    // eğer daha hızlı arama yapmak, bir metni yazarken her harf girişinde aramasını istiyorsan vs,
    // job'a başlamadan, daha önceden bu işlem yapılıyorsa, halihazırda yapılan bir job
    //varsa o job'un iptal edilmesini isteyebiliriz
    private fun getMovies(search:String) {
        job?.cancel()
        job = getMovieUseCase.executeGetMovies(search = search).onEach {
    when (it) {
        is Resource.Success -> {
_state.value = MoviesState(movies = )
        }
        is Resource.Error -> {

        }
        is Resource.Loading -> {

        }
    }
        }.launchIn(viewModelScope)
    }

}