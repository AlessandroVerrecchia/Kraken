package com.example.kraken.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Joke(
    @Expose
    @SerializedName("id")
    var id: String,
    @Expose
    @SerializedName("type")
    var type: String,
    @Expose
    @SerializedName("setup")
    var setup: String,
    @Expose
    @SerializedName("punchline")
    var punchline: String
) : Parcelable