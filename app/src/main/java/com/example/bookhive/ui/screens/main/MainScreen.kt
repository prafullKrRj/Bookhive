package com.example.bookhive.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bookhive.Screens
import com.example.bookhive.model.BooksResponse
import com.example.bookhive.model.Item
import com.example.bookhive.ui.screens.main.components.AppBar
import com.example.bookhive.ui.screens.main.components.BookType
import com.example.bookhive.ui.screens.main.components.BooksRow

@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
    val state = viewModel.state
    
    when (state) {
        MainScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text(
                text = "Error",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            ) }
        }
        MainScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LinearProgressIndicator()
            }
        }

        is MainScreenState.Success -> {
          //  val data = state.books.items[0].volumeInfo.imageLinks.thumbnail
            val map = viewModel.map
            val categories = viewModel.categories
            MUI(
                text = "hello",
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
    text: String,
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
                    println(map)
                    val items: List<Item>? = map[string]?.items
                    if (items != null) {
                        BookType(type = string)
                        Spacer(modifier = Modifier.height(8.dp))
                        BooksRow(items = items) {

                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}