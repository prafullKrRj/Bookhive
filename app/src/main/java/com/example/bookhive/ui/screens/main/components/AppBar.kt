package com.example.bookhive.ui.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.bookhive.R


@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon()
            Spacer(modifier = Modifier.width(6.dp))
            AppName()
        }
      //  MainScreenMenu(modifier = Modifier)
    }
}

@Composable
fun MainScreenMenu(modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        IconButton(
            onClick = {
                expanded = true
            },
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                modifier = Modifier
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {
            DropdownMenuItem(
                text = { Text(text = "Theme") },
                onClick = {
                    openDialog = true
                    expanded = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
            )
        }
    }

    if (openDialog) {
        ThemeSelectorDialog(onClick = {
            openDialog = false
        }) {
            openDialog = false
        }
    }
}


@Composable
fun ThemeSelectorDialog(onClick: (Int) -> Unit, openDialog: () -> Unit) {
    var selected by remember { mutableIntStateOf(0) }
    val colors = arrayOf("Dark", "Light", "System")
    Dialog(onDismissRequest = { openDialog() }) {
       Card(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                repeat(3) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .fillMaxWidth()
                            .clickable { selected = it },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = selected == it, onClick = {
                            selected = it
                        })
                        Text(text = colors[it], fontSize = 16.sp, fontWeight = FontWeight.Normal)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        openDialog()
                    }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        onClick(selected)
                    }) {
                        Text(text = "Ok")
                    }
                }
            }
       }
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