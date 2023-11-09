package com.example.bookhive.ui.screens.more.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(modifier: Modifier = Modifier, title: String, onBack: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = {
                      onBack()
            },
            modifier = Modifier,
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier)
        }
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold)
    }
}