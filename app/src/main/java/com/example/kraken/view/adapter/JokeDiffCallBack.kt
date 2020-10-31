package com.example.kraken.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kraken.model.Joke

class JokeDiffCallBack : DiffUtil.ItemCallback<Joke>() {
    override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem.id == newItem.id
    }
}