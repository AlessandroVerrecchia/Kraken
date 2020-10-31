package com.example.kraken.view.adapter

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_joke.view.*

class JokeViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {
    val container: ConstraintLayout = itemView.container
    val typeTextView: TextView = itemView.joke_type_text_view
    val setupTextView: TextView = itemView.joke_setup_text_view
    val punchlineTextView: TextView = itemView.joke_punchline_text_view
}