package com.example.bookhive.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: SearchViewModel, navController: NavController) {
    var text by rememberSaveable { mutableStateOf("") }
    val state = viewModel.state
    Scaffold(
        topBar = {
            SearchBar {
                text = it
                viewModel.getBooks(text)
            }
        }
    ) { paddingValues ->
        if (text.isNotEmpty()) {
            when (state) {
                SearchState.Error -> {
                    Error()
                }

                SearchState.Loading -> {
                    Loading()
                }

                is SearchState.Success -> {
                    SearchResults(paddingValues = paddingValues, items = state.books.items)
                    println(state.books.items)
                }
            }
        }
    }
}

@Composable
fun SearchResults(paddingValues: PaddingValues, items: List<Item>?) {
    LazyColumn(contentPadding = paddingValues) {
        items?.forEach {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (it?.volumeInfo?.title != null) {
                        Text(text = it.volumeInfo.title)
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onValueChange: (String) -> Unit){
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
                        },
        label = { Text("Label") },
        singleLine = true
    )
}