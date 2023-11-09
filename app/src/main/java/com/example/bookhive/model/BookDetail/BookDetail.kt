package com.example.bookhive.model.BookDetail

import com.example.bookhive.model.BookResponse.AccessInfo
import com.example.bookhive.model.BookResponse.SaleInfo
import com.example.bookhive.model.BookResponse.VolumeInfo
import com.google.gson.annotations.SerializedName

data class BookDetail(
    @SerializedName("accessInfo")
    val accessInfo: AccessInfo,

    @SerializedName("etag")
    val etag: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("kind")
    val kind: String,

    @SerializedName("saleInfo")
    val saleInfo: SaleInfo,

    @SerializedName("selfLink")
    val selfLink: String,

    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo
)