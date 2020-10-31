package com.example.kraken.network

import com.example.kraken.model.Joke
import retrofit2.http.GET

interface IApiService {
    @GET("random_ten")
    suspend fun getTenRandomJokes(): List<Joke>
}