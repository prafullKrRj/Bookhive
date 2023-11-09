package com.example.bookhive.ui.screens.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhive.model.BookResponse.Item

@Composable
fun BookCard(
    modifier: Modifier,
    item: Item,
    onClick: () -> Unit
) {
    val title = item?.volumeInfo?.title
    val desp = item?.volumeInfo?.description
    val data = item?.volumeInfo?.imageLinks?.thumbnail
    Card(
        modifier = modifier
            .width(180.dp)
            .height(280.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick()
            }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            BookImage(data = data)
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xFF3F4948)))),
                verticalArrangement = Arrangement.Bottom
            ){
                BooksDescription(modifier = Modifier.fillMaxSize(), title = title)
            }

        }
    }
}

@Composable
fun BooksDescription(modifier: Modifier = Modifier, title: String?) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(15.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (title != null) {
            var ans = title
            if (title.length > 30) {
                ans = title.substring(0, 29)+"..."
            }
            Text(
                text = ans,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFE0E3E2)
            )
        }else {
            Text(text = "No data available", fontWeight = FontWeight.Light, fontSize = 13.sp)
        }
    }
}
