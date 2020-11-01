package com.example.kraken.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class Speaker(context: Context) : ISpeaker {

    private lateinit var speaker: TextToSpeech

    init {
        speaker = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                speaker.language = Locale.CANADA
            }
        })
    }

    override fun speak(text: String) {
        speaker.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}