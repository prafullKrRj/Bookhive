package com.example.bookhive.ui.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookhive.model.BooksResponse
import com.example.bookhive.ui.screens.commons.BookCard
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading
import com.example.bookhive.ui.screens.more.components.NavigationButton
import com.example.bookhive.ui.screens.more.components.Title

@Composable
fun MoreScreen(title: String, navController: NavController, viewModel: MoreViewModel) {
    val state = viewModel.state
    when (state) {
        MoreScreenState.Error -> {
            Error()
        }
        MoreScreenState.Loading -> {
            Loading()
        }
        is MoreScreenState.Success -> {
            MoreScreenUI(
                modifier = Modifier,
                title = title,
                navController = navController,
                booksResponse = state.books,
                viewModel = viewModel,
                onNext = {
                    viewModel.idx += 10
                    state.idx = viewModel.idx
                    viewModel.resetBooks(viewModel.idx)
                },
                onPrev = {
                    viewModel.idx -= 10
                    state.idx = viewModel.idx
                    viewModel.resetBooks(viewModel.idx)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreenUI(
    modifier: Modifier,
    title: String,
    booksResponse: BooksResponse,
    navController: NavController,
    viewModel: MoreViewModel,
    onNext: () -> Unit,
    onPrev: () -> Unit
) {
    val items = booksResponse.items
    var counter by rememberSaveable {
        mutableIntStateOf(1)
    }
    val lazyGridState = rememberLazyGridState()

    Scaffold(
        topBar = { Title(title = title, modifier = Modifier.padding(start = 12.dp)) {
            navController.popBackStack()
        } }
    ) { padding ->
        println(booksResponse.totalItems)
        Column(modifier = modifier.padding(padding)){
            LazyVerticalGrid(state = lazyGridState, modifier = Modifier, columns = GridCells.Adaptive(180.dp), contentPadding = PaddingValues(16.dp)) {
                if (items != null) {
                    items(items.size) {
                        BookCard(modifier = Modifier.padding(6.dp), item = items[it]) {

                        }
                    }
                }
                if (counter > 1) {
                    item {
                        NavigationButton(text = "Previous") {
                            counter--

                            onPrev()
                        }
                    }
                }
                if (counter < booksResponse.totalItems/10) {
                    item {
                        NavigationButton(text = "Next") {
                            counter++
                            onNext()
                        }
                    }
                }
            }
        }
    }
}