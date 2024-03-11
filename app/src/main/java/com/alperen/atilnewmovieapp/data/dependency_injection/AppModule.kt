package com.alperen.atilnewmovieapp.data.dependency_injection

import com.alperen.atilnewmovieapp.data.remote.MovieAPI
import com.alperen.atilnewmovieapp.data.repository.MovieRepositoryImpl
import com.alperen.atilnewmovieapp.domain.repository.MovieRepository
import com.alperen.atilnewmovieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    fun provideMovieApi():MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api:MovieAPI):MovieRepository {
        return MovieRepositoryImpl(api= api)
    }
}