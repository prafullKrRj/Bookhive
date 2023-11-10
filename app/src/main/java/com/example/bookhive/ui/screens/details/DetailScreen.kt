package com.example.bookhive.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookhive.R
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading
import com.example.bookhive.ui.screens.details.components.BookPhotoGallery

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

@Composable
fun DetailUI(item: Item, navController: NavController, images: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        println(images)
        BookPhotoGallery(images)
    }
}
@Composable
fun DetailBookImage(modifier: Modifier = Modifier, data: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(data).crossfade(true).build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.ic_broken_image),
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Black
                    )
                )
            )
            .fillMaxSize())
    }
}
