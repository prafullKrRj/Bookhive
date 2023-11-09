package com.example.bookhive.network

import com.example.bookhive.model.BookDetail.BookDetail
import com.example.bookhive.model.BookResponse.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int
      //  @Query("key") key: String = API_KEY
    ) : BooksResponse

    @GET("volumes/{bookId}")
    suspend fun getBookById(
        @Path("bookId") bookId: String
    ) : BookDetail
}
