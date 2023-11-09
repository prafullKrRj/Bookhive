package com.example.bookhive.data

import com.example.bookhive.model.BooksResponse
import com.example.bookhive.network.BooksApiService

interface BooksRepository {
    suspend fun searchBooks(query: String, startIndex: Int): BooksResponse
    //suspend fun searchBookById(query: String, startIndex: String): BooksResponse
}
class NetworkBooksDetailRepository (
    private val apiService: BooksApiService
): BooksRepository {

    override suspend fun searchBooks(query: String, startIndex: Int): BooksResponse = apiService.getBooks(
        query = query,
        startIndex = startIndex
    )
}