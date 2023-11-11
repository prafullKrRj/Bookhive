package com.example.bookhive.ui.screens.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhive.R
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.commons.BookImage

@Composable
fun NameAndImageRow(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicDetails(item = item, modifier = Modifier.weight(.5f))
        ImageBox(
            modifier = Modifier
                .weight(.5f)
                .width(180.dp)
                .height(280.dp)
                .padding(8.dp),
            item = item
        )
    }
}
@Composable
private fun BasicDetails(modifier: Modifier = Modifier, item: Item) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (item?.volumeInfo?.title != null) {
                SmallBlockUI(type = "Title", value = item.volumeInfo.title)
            }
            Spacer(modifier = Modifier.height(6.dp))
            if (item?.volumeInfo?.authors != null) {
                SmallBlockUI(type = "Author", value = item.volumeInfo.authors[0])
            }
            if (item?.volumeInfo?.language != null) {
                SmallBlockUI(type = "Language", value = item.volumeInfo.language)

            }
            if (item?.volumeInfo?.publisher != null) {
                SmallBlockUI(type = "Publisher", value = item.volumeInfo.publisher)

            }
        }
    }
}

@Composable
fun SmallBlockUI(type: String, value: String) {
    Text(text = type, fontSize = 22.sp, fontWeight = FontWeight.Medium)
    Text(
        text = value,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif
    )
    BasicDetailsDivider()
}
@Composable
private fun BasicDetailsDivider() {
    Divider(
        Modifier
            .padding(end = 8.dp)
            .padding(vertical = 8.dp)
            .height(1.dp))
}
@Composable
private fun ImageBox(modifier: Modifier, item: Item) {
    Box(
        modifier = modifier,
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