package com.example.kraken.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kraken.R
import com.example.kraken.model.Joke
import com.example.kraken.network.ApiClient
import com.example.kraken.network.IApiService
import com.example.kraken.useCase.FetchJokesUseCase
import com.example.kraken.useCase.IFetchJokesUseCase
import com.example.kraken.utils.ISpeaker
import com.example.kraken.utils.Speaker
import com.example.kraken.utils.Status
import com.example.kraken.view.adapter.IHasReachEndOfListListener
import com.example.kraken.view.adapter.IJokeClickListener
import com.example.kraken.view.adapter.ISpeakerClickListener
import com.example.kraken.view.adapter.JokeAdapter
import com.example.kraken.view.picture.PictureFragment
import com.example.kraken.viewModel.MainViewModel
import com.example.kraken.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IHasReachEndOfListListener, IJokeClickListener,
    ISpeakerClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: JokeAdapter
    private val randomPictureFragment = PictureFragment()
    private lateinit var speaker: ISpeaker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTextToSpeech()
        setupViewModel()
        setupRecyclerView()
        fetchAndObserveJokes()
    }

    private fun setupTextToSpeech() {
        speaker = Speaker(this)
    }

    private fun setupViewModel() {
        val fetchJokesUseCase: IFetchJokesUseCase =
            FetchJokesUseCase(ApiClient.apiClient().create(IApiService::class.java))

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(fetchJokesUseCase)
        ).get(MainViewModel::class.java)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = JokeAdapter(this, this)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    hasReachEndOfList()
                }
            }
        })
    }


    private fun fetchAndObserveJokes() {
        viewModel.fetchUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        resource.data?.let { jokes -> retrieveList(jokes.data ?: emptyList()) }
                    }
                    Status.ERROR -> {
                        showProgress(false)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        showProgress(true)
                    }
                }
            }
        })
    }

    private fun retrieveList(jokes: List<Joke>) {
        if (jokes.isNotEmpty()) {
            adapter.updateList(jokes)
        }
    }

    private fun showProgress(status: Boolean) {
        if (status) {
            show_progress.visibility = View.VISIBLE
        } else {
            show_progress.visibility = View.GONE
        }
    }

    override fun hasReachEndOfList() {
        fetchAndObserveJokes()
    }

    override fun onJokeClick() {
        if (randomPictureFragment.isVisible.not()) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, randomPictureFragment, "PictureFragment")
                .commit()
        }
    }

    override fun onSpeakerClick(text: String) {
        speaker.speak(text)
    }

    override fun onBackPressed() {
        if (randomPictureFragment.isVisible) {
            supportFragmentManager
                .beginTransaction()
                .remove(randomPictureFragment)
                .commit()
        } else {
            super.onBackPressed()
        }
    }

}