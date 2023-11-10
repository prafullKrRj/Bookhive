package com.example.bookhive.data

import com.example.bookhive.model.BookResponse.BooksResponse
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.network.BooksApiService

interface BooksRepository {
    suspend fun searchBooks(query: String, startIndex: Int): BooksResponse
    suspend fun getBookById(id: String) : Item

    suspend fun getBookByName(name: String) : BooksResponse
}
class NetworkBooksDetailRepository (
    private val apiService: BooksApiService
): BooksRepository {

    override suspend fun searchBooks(query: String, startIndex: Int): BooksResponse = apiService.getBooks(
        query = query,
        startIndex = startIndex
    )
    override suspend fun getBookById(id: String): Item = apiService.getBookById(id)
    override suspend fun getBookByName(name: String): BooksResponse = apiService.getBookByName(name)
}