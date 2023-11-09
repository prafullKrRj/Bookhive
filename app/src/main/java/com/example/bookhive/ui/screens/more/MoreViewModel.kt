package com.example.bookhive.ui.screens.more

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

sealed interface MoreScreenState {
    data class Success(val books: BooksResponse, var idx: Int): MoreScreenState
    object Error: MoreScreenState
    object Loading: MoreScreenState
}
class MoreViewModel(
    private val booksRepository: BooksRepository,
    private var query: String
) : ViewModel(){

    var state: MoreScreenState by mutableStateOf(MoreScreenState.Loading)
    var idx by mutableIntStateOf(1)
    init {
        getBooks(query, idx)
    }
    private fun getBooks(query: String, idx: Int) {
        viewModelScope.launch {
            state = try {
                MoreScreenState.Success(booksRepository.searchBooks("subject:$query", idx), idx = idx)
            } catch (e: HttpException) {
                MoreScreenState.Error
            } catch (e: IOException) {
                MoreScreenState.Loading
            }
        }
    }
    fun resetBooks(idx: Int) {
        getBooks(query = query, idx)
    }
}