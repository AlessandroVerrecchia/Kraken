package com.example.kraken.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kraken.R
import com.example.kraken.model.Joke


class JokeAdapter(
    private val jokeClickListener: IJokeClickListener,
    private val speakerClickListener: ISpeakerClickListener
) :
    ListAdapter<Joke, JokeViewHolder>(JokeDiffCallBack()) {

    private var listOfJokes = mutableListOf<Joke>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_joke,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfJokes.size
    }

    fun updateList(jokes: List<Joke>) {
        listOfJokes.addAll(jokes.toMutableList())
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = listOfJokes[position]
        holder.apply {
            typeTextView.text = joke.type
            setupTextView.text = joke.setup
            punchlineTextView.text = joke.punchline
            container.setOnClickListener {
                jokeClickListener.onJokeClick()
            }
            speakerImageView.setOnClickListener {
                val jokeText: String = "${joke.setup}... ${joke.punchline}"
                speakerClickListener.onSpeakerClick(jokeText)
            }
        }
    }
}