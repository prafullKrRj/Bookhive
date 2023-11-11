package com.example.bookhive.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading

@Composable
fun DetailScreen(navController: NavController, txt:String, viewModel: DetailViewModel) {
    val state = viewModel.state
    when (state) {
        DetailScreenState.Error -> {
            Error()
        }
        DetailScreenState.Loading -> {
            Loading()
        }
        is DetailScreenState.Success -> {
            DetailUI(
                item = state.books,
                navController = navController,
                images = viewModel.getImageList(state.books)
            )
        }
    }
}

