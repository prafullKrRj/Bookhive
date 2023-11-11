package com.example.bookhive.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhive.data.BooksRepository
import com.example.bookhive.model.BookResponse.BooksResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface SearchState {
    data class Success(val books: BooksResponse): SearchState
    object Error: SearchState
    object Loading: SearchState
}
class SearchViewModel(
    private val booksRepository: BooksRepository
) : ViewModel()
{
    var state: SearchState by mutableStateOf(SearchState.Loading)
        private set

    var idx by mutableIntStateOf(0)

    var lastQuery by mutableStateOf("")
    fun getBooks(query: String, idx: Int) {
        viewModelScope.launch {
            lastQuery = query
            state = try {
                SearchState.Success(booksRepository.searchBooks(query, idx))
            } catch (e: HttpException) {
                SearchState.Error
            } catch (e: IOException) {
                SearchState.Loading
            }
        }
    }
    fun resetBooks(query: String, idx: Int) {
        getBooks(query = query, idx)
    }
}