package com.example.bookhive.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
            DetailUI(txt = state.books.volumeInfo.imageLinks.thumbnail)
        }
    }
}

@Composable
fun DetailUI(txt: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data("https" + txt.substring(4))
                .crossfade(true).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}