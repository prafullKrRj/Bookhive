package com.example.bookhive.data

import com.example.bookhive.constants.Constant.API_KEY
import com.example.bookhive.constants.Constant.BASE_URL
import com.example.bookhive.network.BooksApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bookDetailRepository: BooksRepository
}
class DefaultAppContainer: AppContainer {
    private val baseUrl = BASE_URL
    private val apiKey = API_KEY
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    override val bookDetailRepository: BooksRepository by lazy {
        NetworkBooksDetailRepository(apiService = retrofitService)
    }
}
