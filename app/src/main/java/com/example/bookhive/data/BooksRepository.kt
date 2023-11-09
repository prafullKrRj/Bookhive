package com.example.bookhive.data

import com.example.bookhive.model.BooksResponse
import com.example.bookhive.network.BooksApiService

interface BooksRepository {
    suspend fun searchBooks(query: String): BooksResponse
    //suspend fun searchBookById(query: String, startIndex: String): BooksResponse
}
class NetworkBooksDetailRepository (
    private val apiService: BooksApiService
): BooksRepository {

    override suspend fun searchBooks(query: String): BooksResponse = apiService.getBooks(query = query)
}