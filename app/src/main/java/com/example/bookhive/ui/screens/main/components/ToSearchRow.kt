package com.example.bookhive.ui.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhive.Screens

@Composable
fun ToSearchRow (onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(10.dp),
            )
            .background(MaterialTheme.colorScheme.secondaryContainer)

            .height(35.dp)
            .clickable {
                onClick()
            }.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            Modifier.size(20.dp)
        )
        Text(
            text = "Search",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(start = 8.dp)
                .padding(bottom = 2.dp)
        )
    }
}