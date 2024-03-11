package com.alperen.atilnewmovieapp.presentation.movies.views

import com.alperen.atilnewmovieapp.domain.model.Movie

data class MoviesState (
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search:String="Pianist"
        )
