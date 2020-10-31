package com.example.kraken.useCase

import com.example.kraken.model.Joke
import com.example.kraken.utils.Resource

interface IFetchJokesUseCase {
    suspend fun execute(): Resource<List<Joke>>
}