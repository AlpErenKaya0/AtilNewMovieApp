package com.alperen.atilnewmovieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperen.atilnewmovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.alperen.atilnewmovieapp.presentation.movies.MoviesEvent
import com.alperen.atilnewmovieapp.presentation.movies.MoviesState
import com.alperen.atilnewmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase:GetMovieUseCase
): ViewModel() {

    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state: State<MoviesState> = _state
    private var job : Job?= null
    // eğer daha hızlı arama yapmak, bir metni yazarken her harf girişinde aramasını istiyorsan vs,
    // job'a başlamadan, daha önceden bu işlem yapılıyorsa, halihazırda yapılan bir job
    //varsa o job'un iptal edilmesini isteyebiliriz

    init {
        getMovies(_state.value.search)
    }
    private fun getMovies(search:String) {
        job?.cancel()
        job = getMovieUseCase.executeGetMovies(search = search).onEach {
    when (it) {
        is Resource.Success -> {
        _state.value = MoviesState(movies = it.data ?: emptyList() )
        }
        is Resource.Error -> {
            _state.value = MoviesState(error = it.message ?: "Error!")
            //ya da _state.value = _state.value.copy() diyerek
            //YENİ BİR INSTANCE oluşturmadan da halledebiliriz.

        }
        is Resource.Loading -> {
        _state.value = MoviesState(isLoading = true)
        }
    }
        }.launchIn(viewModelScope)
    }
fun onEvent(event: MoviesEvent) {
    when(event) {
        is MoviesEvent.Search -> {
            getMovies(event.searchString)
        }
    }
}
}