package com.example.bookhive.model.BookDetail

import com.example.bookhive.model.BookResponse.ListPriceX
import com.example.bookhive.model.BookResponse.RetailPrice
import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("finskyOfferType")
    val finskyOfferType: Double,
    @SerializedName("listPrice")
    val listPrice: ListPriceX,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice
)