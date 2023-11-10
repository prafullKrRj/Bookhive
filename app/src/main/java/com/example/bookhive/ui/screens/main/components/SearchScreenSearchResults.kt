package com.example.bookhive.ui.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookhive.Screens
import com.example.bookhive.model.BookResponse.Item

@Composable
fun SearchResults(paddingValues: PaddingValues, items: List<Item>?, navController: NavController) {
    LazyColumn(contentPadding = paddingValues) {
        items?.forEach {
            item {
                if (it?.volumeInfo?.title != null) {

                    Row(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            navController.navigate(Screens.DETAIL_SCREEN.name+"/${it.id}")
                        }
                        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val text = if (it.volumeInfo.title.length >= 30) it.volumeInfo.title.substring(0, 30) + "..." else {
                            it.volumeInfo.title
                        }
                        Text(text = text)
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}
