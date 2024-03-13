package com.alperen.atilnewmovieapp.presentation.movies

sealed class MoviesEvent {
    data class Search (val searchString: String) : MoviesEvent()
}