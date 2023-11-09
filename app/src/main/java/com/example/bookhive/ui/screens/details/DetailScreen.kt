package com.example.bookhive.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookhive.R
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
            val y = state?.books?.volumeInfo?.imageLinks
            DetailUI(txt = y?.large)
        }
    }
}

@Composable
fun DetailUI(txt: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val data = "https" + txt?.substring(4)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(data)
                .crossfade(true).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img)
        )
    }
}