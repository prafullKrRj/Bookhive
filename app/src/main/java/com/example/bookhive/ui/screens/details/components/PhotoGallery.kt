package com.example.bookhive.ui.screens.details.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookhive.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookPhotoGallery (list: List<String>) {
    val state = rememberPagerState {
        list.size
    }
    if (list.isEmpty()) {
        return
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = state,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
            pageSpacing = 6.dp,
            modifier = Modifier.fillMaxWidth(),
            pageSize = PageSize.Fill
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                BookImages(link = list[it])
                Row(
                    modifier = Modifier.padding(bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(list.size) {
                        PageIndicator(idx = it, currentPage = state.currentPage)
                    }
                }
              /*  Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Transparent),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    FilledIconButton(
                        onClick = {

                        },
                        modifier = Modifier.clip(CircleShape).size(35.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(colorScheme.inversePrimary)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier,
                            tint = colorScheme.primary
                        )
                    }
                }*/
            }
        }
    }
}

@Composable
fun PageIndicator(idx: Int, currentPage: Int) { 
    Box(
        modifier = Modifier
            .padding(3.dp)
            .clip(CircleShape)
            .size(10.dp)
            .border(2.dp, colorScheme.inversePrimary, CircleShape)
            .background(color = if (idx == currentPage) colorScheme.inversePrimary else Transparent),
    )
}
@Composable
fun BookImages(link: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(link).crossfade(true).build(),
        placeholder = painterResource(id = R.drawable.loading_img),
        error = painterResource(id = R.drawable.ic_broken_image),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}
@Preview
@Composable
fun BookPhotoGalleryPreview() {
    BookPhotoGallery(listOf(
       "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51Zymoq7UnL._AC_SY400_.jpg",
    ))
}