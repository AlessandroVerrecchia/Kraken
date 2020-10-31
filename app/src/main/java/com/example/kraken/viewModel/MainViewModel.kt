package com.example.kraken.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kraken.useCase.IFetchJokesUseCase
import com.example.kraken.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val fetchJokesUseCase: IFetchJokesUseCase) : ViewModel() {
    fun fetchUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.success(data = fetchJokesUseCase.execute()))
    }
}