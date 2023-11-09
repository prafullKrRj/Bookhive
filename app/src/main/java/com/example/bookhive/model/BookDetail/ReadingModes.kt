package com.example.bookhive.model.BookDetail

import com.google.gson.annotations.SerializedName

data class ReadingModes(
    @SerializedName("image")
    val image: Boolean,

    @SerializedName("text")
    val text: Boolean
)