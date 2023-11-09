package com.example.bookhive.ui.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhive.R


@Composable
fun AppBar(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(.85f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon()
            Spacer(modifier = Modifier.width(6.dp))
            AppName()
        }
        SearchIcon(Modifier.weight(.15f)) {
            onClick()
        }
    }
}
@Composable
private fun SearchIcon(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier)
    }
}
@Composable
fun AppIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
        modifier = modifier
            .size(45.dp)
            .clip(CircleShape)
    )
}
@Composable
fun AppName(modifier: Modifier = Modifier) {
    Text(
        text = "BookHive",
        fontSize = 25.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )
}