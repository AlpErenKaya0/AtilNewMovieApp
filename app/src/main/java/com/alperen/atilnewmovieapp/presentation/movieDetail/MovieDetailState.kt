package com.alperen.atilnewmovieapp.presentation.movieDetail

import com.alperen.atilnewmovieapp.domain.model.MovieDetail

data class MovieDetailState (
val isLoading : Boolean = false,
val movie: MovieDetail ? = null,
val error : String = ""
)