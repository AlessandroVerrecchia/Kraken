package com.example.kraken.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kraken.network.IApiService
import com.example.kraken.useCase.FetchJokesUseCase
import com.example.kraken.useCase.IFetchJokesUseCase
import java.lang.IllegalArgumentException

class ViewModelFactory(private val fetchJokesUseCase: IFetchJokesUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(fetchJokesUseCase)as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}