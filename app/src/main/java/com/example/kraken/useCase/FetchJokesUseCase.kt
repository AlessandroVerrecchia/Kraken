package com.example.kraken.useCase

import com.example.kraken.model.Joke
import com.example.kraken.network.IApiService
import com.example.kraken.utils.Resource
import com.example.kraken.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchJokesUseCase(private val apiService: IApiService) : IFetchJokesUseCase {
    override suspend fun execute(): Resource<List<Joke>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource(Status.SUCCESS, apiService.getTenRandomJokes(), null)
            } catch (exception: Exception) {
                Resource(Status.ERROR, null, exception.message ?: "Error occured")
            }
        }
    }
}