package com.example.bookhive.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhive.data.BooksRepository
import com.example.bookhive.model.BookDetail.BookDetail
import com.example.bookhive.model.BookResponse.BooksResponse
import com.example.bookhive.ui.screens.more.MoreScreenState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface DetailScreenState {
    data class Success(val books: BookDetail): DetailScreenState
    object Error: DetailScreenState
    object Loading: DetailScreenState
}
class DetailViewModel(
    private val booksRepository: BooksRepository,
    private val bookId: String
): ViewModel() {

    var state: DetailScreenState by mutableStateOf(DetailScreenState.Loading)

    init {
        getBookById(bookId)
    }
    private fun getBookById(id: String) {
        viewModelScope.launch {
            state = try {
                DetailScreenState.Success(booksRepository.getBookById(id))
            } catch (e: HttpException) {
                DetailScreenState.Error
            } catch (e: IOException) {
                DetailScreenState.Loading
            }
        }
    }
}