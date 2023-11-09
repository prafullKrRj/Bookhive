package com.example.bookhive.ui.screens.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun MainScreen(navController: NavHostController) {
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
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MUI(
    navController: NavHostController,
    map: MutableMap<String, BooksResponse>,
    categories: Array<String>,
) {
    Scaffold(
        topBar = {
            AppBar {
                navController.navigate(Screens.SEARCH_SCREEN.name)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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