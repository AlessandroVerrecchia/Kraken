package com.example.kraken.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kraken.R
import com.example.kraken.view.utils.loadImage
import kotlinx.android.synthetic.main.fragment_picture.*
import kotlin.random.Random


class PictureFragment : Fragment() {

    companion object {
        const val RANDOM_IMAGE_URL = "https://picsum.photos/"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage(picture_image_view, "$RANDOM_IMAGE_URL${Random.nextInt(250, 1000)}")
    }

}