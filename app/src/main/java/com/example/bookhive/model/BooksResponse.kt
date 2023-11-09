package com.example.bookhive.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Long
)