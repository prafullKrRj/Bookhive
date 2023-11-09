package com.example.bookhive.ui.screens.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.commons.BookCard

@Composable
fun BooksRow(
    modifier: Modifier = Modifier,
    items: List<Item>?,
    seeMore: () -> Unit,
    toDetailScreen: (String) -> Unit
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(6.dp)
    ) {
        if (items?.size!! >= 8) {
            items(8) {
                if (items?.get(it) != null) {
                    BookCard(
                        modifier = Modifier.padding(start = if (it == 0) 0.dp else 6.dp),
                        item = items[it]
                    ) {
                        toDetailScreen(items[it].id)
                    }
                }
            }
        }
        else {
            items(items.size) {
                if (items?.get(it) != null) {
                    BookCard(
                        modifier = Modifier.padding(start = if (it == 0) 0.dp else 6.dp),
                        item = items[it]
                    ) {
                        toDetailScreen(items[it].selfLink)
                    }
                }
            }
        }
        item {
            SeeMoreButton {
                seeMore()
            }
        }
    }
}
@Composable
fun SeeMoreButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = {
            onClick()
        },
        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(CircleShape)
            .size(40.dp),
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
        )
    }
}