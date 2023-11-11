package com.example.bookhive.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import com.example.bookhive.Screens
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading
import com.example.bookhive.ui.screens.search.components.SearchField
import com.example.bookhive.ui.screens.search.components.SearchResults

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController
) {
    var text by rememberSaveable { mutableStateOf("") }
    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    Scaffold(
        topBar = {
            SearchField(
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                onSearched = {
                             viewModel.getBooks(it, 0)
                    keyboardController?.hide()
                    text = it
                    navController.navigate(route = Screens.SEARCH_RESULTS_SCREEN.name + "/$it")
                },
                keyboardController = keyboardController,
                focusRequester = focusRequester,
                onValueChange = {
                    text = it
                    viewModel.getBooks(it, 0)
                })
        }
    ) { paddingValues ->
        if (text.isNotEmpty()) {
            when (state) {
                SearchState.Error -> {
                    Error {
                        viewModel.getBooks(text, 0)
                    }
                }

                SearchState.Loading -> {
                    Loading()
                }

                is SearchState.Success -> {
                    SearchResults(
                        paddingValues = paddingValues,
                        items = state.books.items,
                        navController
                    )
                    println(state.books.items)
                }
            }
        }
    }
}

