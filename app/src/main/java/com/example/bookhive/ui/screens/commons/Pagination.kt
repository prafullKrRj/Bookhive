package com.example.bookhive.ui.screens.commons

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Pagination(size: Int, onButtonClick: (Int) -> Unit) {
    val count = size/10
    var selected by rememberSaveable {
        mutableIntStateOf(1)
    }
    var array: IntArray by rememberSaveable {
        mutableStateOf(intArrayOf(1, 2, 3, 4, 5))
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedButton(
            onClick = {
                if (selected != 1) {
                    if (selected == array[0]) {
                        array = array.map { it - 1 }.toIntArray()
                    }
                    selected--
                    onButtonClick(selected)
                }
            },
            modifier = Modifier.weight(.22f)
        ) {
            Text("Prev")
        }
        Row(
            modifier = Modifier.weight(.78f).animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.weight(0.80f)
            ) {
                array.forEach{
                    TextButton(
                        {
                            selected = it
                        },
                        Modifier
                            .padding(end = 4.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(colorScheme.secondaryContainer)
                            .size(38.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = if (selected == it) {
                                colorScheme.tertiaryContainer
                            } else {
                                colorScheme.secondaryContainer
                            },
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = "$it", fontSize = if (it > 99) {
                                9.sp
                            } else if (it > 9) {
                                12.sp
                            } else {
                                16.sp
                            }
                            )
                        }
                    }
                }
            }
            if (selected != array[array.size-1] || array[array.size-1] < count) {
                OutlinedButton(
                    onClick = {
                        if (selected == array[array.size-1]) {
                            array = array.map { it+1 }.toIntArray()
                        }
                        selected++
                        onButtonClick(selected)
                    },
                    Modifier.weight(.30f)
                ) {

                    Text("Next")
                }
            }
        }
    }
}

@Preview
@Composable
fun PaginationPreview() {
    Pagination(5210) {

    }
}
