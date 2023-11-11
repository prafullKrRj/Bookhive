package com.example.bookhive.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.details.components.BookPhotoGallery
import com.example.bookhive.ui.screens.details.components.Description
import com.example.bookhive.ui.screens.details.components.NameAndImageRow
import com.example.bookhive.ui.screens.details.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUI(item: Item, navController: NavController, images: List<String>) {
    Scaffold(
        topBar = {
            TopBar(title = item.volumeInfo.title) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                // start image basic thumbnail
                item {
                    NameAndImageRow(item = item)
                }
                // Description
                item {
                    Description(item = item)
                }
                item {
                    BookPhotoGallery(list = images)
                }
            }
        }
    }
}


fun getString(string: String): String {
    val spannedString = HtmlCompat.fromHtml(string, HtmlCompat.FROM_HTML_MODE_LEGACY)
    return spannedString.toString()
}
