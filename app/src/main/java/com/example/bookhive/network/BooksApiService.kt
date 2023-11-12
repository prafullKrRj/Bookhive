package com.example.bookhive.network

import com.example.bookhive.constants.Constant.API_KEY
import com.example.bookhive.model.BookResponse.BooksResponse
import com.example.bookhive.model.BookResponse.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int,
        @Query("key") key: String = API_KEY
    ) : BooksResponse

    @GET("volumes/{bookId}")
    suspend fun getBookById(
        @Path("bookId") bookId: String,
        @Query("key") key: String = API_KEY
    ) : Item

    @GET("volumes")
    suspend fun getBookByName (
        @Query("q") query: String,
        @Query("key") key: String = API_KEY
    ) : BooksResponse
}
