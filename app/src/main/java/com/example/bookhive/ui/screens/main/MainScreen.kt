package com.example.bookhive.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bookhive.Screens
import com.example.bookhive.model.BookResponse.BooksResponse
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading
import com.example.bookhive.ui.screens.main.components.AppBar
import com.example.bookhive.ui.screens.main.components.BookType
import com.example.bookhive.ui.screens.main.components.BooksRow

@Composable
fun MainScreen(navController: NavHostController, changeTheme: () -> Unit) {
    val viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
    val state = viewModel.state
    
    when (state) {
        MainScreenState.Error -> {
            Error()
        }
        MainScreenState.Loading -> {
            Loading()
        }
        is MainScreenState.Success -> {
            val map = viewModel.map
            val categories = viewModel.categories
            MUI(
                navController = navController,
                map = map,
                categories = categories
            ) {
                changeTheme()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MUI(
    navController: NavHostController,
    map: MutableMap<String, BooksResponse>,
    categories: Array<String>,
    changeTheme: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBar {
                changeTheme()
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
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
                            navController.navigate(route = Screens.SEARCH_SCREEN.name)
                        }.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
                }
            }
            item { 
                categories.forEachIndexed {
                    index, string ->

                    val items: List<Item>? = map[string]?.items

                    if (items != null) {
                        BookType(type = string)

                        Spacer(modifier = Modifier.height(8.dp))

                        BooksRow(
                            items = items,
                            seeMore = {
                                navController.navigate(route = Screens.SEE_MORE_SCREEN.name + "/$string")
                            },
                            toDetailScreen = {
                                navController.navigate(route = Screens.DETAIL_SCREEN.name + "/$it")
                            }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}