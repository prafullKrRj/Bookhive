package com.example.bookhive.network

import com.example.bookhive.constants.Constant.API_KEY
import com.example.bookhive.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
      //  @Query("key") key: String = API_KEY
    ) : BooksResponse
}
