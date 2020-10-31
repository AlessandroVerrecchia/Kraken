package com.example.kraken.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kraken.model.Joke
import com.example.kraken.useCase.IFetchJokesUseCase
import com.example.kraken.utils.Resource
import com.example.kraken.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainViewModel(private val fetchJokesUseCase: IFetchJokesUseCase) : ViewModel() {
    fun fetchUsers()= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(Resource.success(data=fetchJokesUseCase.execute()))
    }
}