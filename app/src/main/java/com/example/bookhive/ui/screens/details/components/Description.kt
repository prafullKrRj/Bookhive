package com.example.bookhive.ui.screens.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.details.getString

@Composable
fun Description(item: Item) {
    if (item.volumeInfo?.description != null) {
        Column(Modifier.padding(16.dp)){
            Title(title = "Description")
            if (item.volumeInfo.description.length > 650) {
                var ans by remember {
                    mutableStateOf(item.volumeInfo.description.substring(0, 650))
                }
                var expanded by remember {
                    mutableStateOf(false)
                }

                Text(text = getString(ans).trim(), fontSize = 16.sp, modifier = Modifier.animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ))
                Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    TextButton(onClick = {
                        ans = if (expanded) item.volumeInfo.description.substring(0, 700)+"...."
                        else item.volumeInfo.description
                        expanded = !expanded
                    }) {
                        Text(text = if (expanded) "see less" else "see more")
                    }
                }
            }else {
                Text(text = getString(item.volumeInfo.description).trim(), fontSize = 16.sp)
            }
        }
    }
}