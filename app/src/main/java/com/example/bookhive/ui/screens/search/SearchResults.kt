package com.example.bookhive.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookhive.Screens
import com.example.bookhive.model.BookResponse.BooksResponse
import com.example.bookhive.model.BookResponse.Item
import com.example.bookhive.ui.screens.commons.BookCard
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading
import com.example.bookhive.ui.screens.commons.Pagination
import com.example.bookhive.ui.screens.search.components.SearchField

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchResultsScreen(
    viewModel: SearchViewModel,
    navController: NavController
){

    when (val state = viewModel.state) {
        SearchState.Error -> {
            Error()
        }
        SearchState.Loading -> {
            Loading()
        }
        is SearchState.Success -> {
            var text by rememberSaveable { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusRequester = remember { FocusRequester() }

            val response: BooksResponse = state.books
            val items: List<Item> = response.items
            Scaffold(
                topBar = {
                    SearchField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        onSearched = {
                            viewModel.getBooks(it, 0)
                            keyboardController?.hide()
                            text = it
                            navController.popBackStack()
                            navController.navigate(Screens.SEARCH_RESULTS_SCREEN.name + "/$it")
                        },
                        keyboardController = keyboardController,
                        focusRequester = focusRequester,
                        onValueChange = {
                            text = it
                        })
                },
                bottomBar = {
                    Pagination(size = response.totalItems/10) {
                        viewModel.resetBooks(query = viewModel.lastQuery, it)
                    }
                },
            ) {  paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues),
                ) {
                    ResultItems(navController = navController, items = items)
                }
            }
        }
    }
}

@Composable
fun ResultItems(navController: NavController, items: List<Item>) {
    val gridState = rememberLazyGridState()
    LazyVerticalGrid(state = gridState,
        modifier = Modifier,
        columns = GridCells.Adaptive(180.dp),
    ) {
        if (items != null) {
            items(items.size) {
                BookCard(modifier = Modifier.padding(6.dp), item = items[it]) {
                    navController.navigate(Screens.DETAIL_SCREEN.name + "/${items[it].id}")
                }
            }
        }
    }
}