package com.example.kraken.view.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadImage(imageView: ImageView?, url: String?) {
    imageView ?: return
    Glide.with(imageView.context)
        .load(url)
        .fitCenter()
        .into(imageView)
}
