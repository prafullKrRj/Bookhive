package com.example.bookhive.ui.screens.main

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookhive.BookApplication
import com.example.bookhive.data.BooksRepository
import com.example.bookhive.model.BooksResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface MainScreenState {
    data class Success(val books: BooksResponse): MainScreenState
    object Error: MainScreenState
    object Loading: MainScreenState
}
class MainScreenViewModel(
    private val booksRepository: BooksRepository
): ViewModel() {

    var state: MainScreenState by mutableStateOf(MainScreenState.Loading)
        private set

    val categories = arrayOf(
        "Religion",
        "Fiction",
        "Mystery",
        "Science Fiction",
        "Fantasy",
        "Romance",
        "Thriller",
        "Historical Fiction",
        "Biography",
        "Self-Help",
        "Business & Economics",
        "History",
    )
    var map: MutableMap<String, BooksResponse> = mutableMapOf()
    init {
        for (i in categories) {
            getBooks(i)
        }
    }
    private fun getBooks(query: String){
        viewModelScope.launch {
            state = try {
                MainScreenState.Success(booksRepository.searchBooks("subject:$query"))
            } catch (e: HttpException) {
                MainScreenState.Error
            } catch (e: IOException) {
                MainScreenState.Loading
            }
            when (state) {
                MainScreenState.Error -> TODO()
                MainScreenState.Loading -> TODO()
                is MainScreenState.Success -> {
                    map[query] = (state as MainScreenState.Success).books
                }
            }
        }
    }
    suspend fun getBooksByCategory(query: String): BooksResponse {
        return booksRepository.searchBooks(query)
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val booksRepository = application.container.bookDetailRepository
                MainScreenViewModel(booksRepository = booksRepository)
            }
        }
    }
}