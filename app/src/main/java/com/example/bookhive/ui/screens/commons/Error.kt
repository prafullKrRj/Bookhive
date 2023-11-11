package com.example.bookhive.ui.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhive.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.time.delay

@Composable
fun Error(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_error_outline_24),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = colorScheme.onSurfaceVariant
        )
        Text(
            text = "Something went wrong,\nCheck your internet connection",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = {
            onRetry()
        }, colors = ButtonDefaults.buttonColors(containerColor = colorScheme.secondaryContainer)) {
            Text(text = "Retry", color = colorScheme.onSecondaryContainer)
        }
    }
}