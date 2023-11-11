package com.example.bookhive.ui.screens.details

import androidx.compose.material3.TextButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.commons.BookImage
import com.example.bookhive.ui.screens.details.components.BookPhotoGallery
import com.example.bookhive.ui.screens.details.components.Title

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

@Composable
fun Description(item: Item) {
    var ans by remember {
        mutableStateOf(getString(item.volumeInfo.description.trim()))
    }
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Title(title = "Description")
        if (ans.length > 500) {
            Text(text = ans.substring(0, 500) + "...")
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {

                    }
                ) {
                    Text("see more")
                }
            }
        } else {
            Text(text = ans)
        }
    }
}
fun getString(string: String): String {
    val spannedString = HtmlCompat.fromHtml(string, HtmlCompat.FROM_HTML_MODE_LEGACY)
    return spannedString.toString()
}
@Composable
fun NameAndImageRow(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(.5f)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Title", fontSize = 24.sp, fontWeight = FontWeight.Medium)
                Text(text = item.volumeInfo.title, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Language: ${item.volumeInfo.language}")
            }
        }
        Box(
            modifier = Modifier
                .weight(.5f)
                .width(180.dp)
                .height(280.dp),
            contentAlignment = Alignment.Center,
        ) {
            BookImage(
                data = item?.volumeInfo?.imageLinks?.thumbnail,
                modifier = Modifier.clip(
                    RoundedCornerShape(8.dp),
                ),
            )
        }
    }
}
@Composable
fun TopBar(title: String, onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(
            onClick = {
               onBack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back button"
            )
        }
        Text(
            modifier = Modifier,
            text = if (title.length > 30 ) title.substring(0, 31)+"..." else title,
            style = typography.titleLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.W400
        )
    }
}