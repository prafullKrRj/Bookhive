package com.example.bookhive.ui.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookhive.R

@Composable
fun BookImage(modifier: Modifier = Modifier, data: String?) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        if (data == null) {
            Image (painter = painterResource(id = R.drawable.ic_broken_image), contentDescription = null, Modifier.fillMaxSize())
        } else {
            val data = "https://"+data.substring(7)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}