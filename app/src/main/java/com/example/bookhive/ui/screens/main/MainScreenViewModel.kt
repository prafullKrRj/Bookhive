package com.example.bookhive.ui.screens.main

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
import com.example.bookhive.model.BookResponse.BooksResponse
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
        "Comics",
        "Art",
        "Children",
        "Religion",
        "Cooking",
        "Fiction",
        "Mystery",
        "Science Fiction",
        "Thriller",
        "Historical Fiction",
        "Biography",
        "Self-Help",
        "Business & Economics",
        "History",
    )
    private val numbers = listOf(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
    var map: MutableMap<String, BooksResponse> = mutableMapOf()
    init {
        for (i in categories) {
            getBooks(i, numbers.random())
        }
    }
    private fun getBooks(query: String, idx: Int){
        viewModelScope.launch {
            state = try {
                MainScreenState.Success(booksRepository.searchBooks("category:$query", idx))
            } catch (e: HttpException) {
                MainScreenState.Error
            } catch (e: IOException) {
                MainScreenState.Error
            }
            when (state) {
                MainScreenState.Error -> {

                }
                MainScreenState.Loading -> {

                }
                is MainScreenState.Success -> {
                    map[query] = (state as MainScreenState.Success).books
                }
            }
        }
    }
    fun retryStart() {
        for (i in categories) {
            getBooks(i, numbers.random())
        }
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