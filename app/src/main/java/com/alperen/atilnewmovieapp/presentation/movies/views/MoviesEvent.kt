package com.alperen.atilnewmovieapp.presentation.movies.views

sealed class MoviesEvent {
    data class Search (val searchString: String) : MoviesEvent()
}