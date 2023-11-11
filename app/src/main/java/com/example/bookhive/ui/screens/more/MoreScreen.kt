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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookhive.Screens
import com.example.bookhive.model.BookResponse.BooksResponse
import com.example.bookhive.ui.screens.commons.BookCard
import com.example.bookhive.ui.screens.commons.Error
import com.example.bookhive.ui.screens.commons.Loading
import com.example.bookhive.ui.screens.commons.Pagination
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
            ItemsUI(
                modifier = Modifier,
                title = title,
                booksResponse = state.books,
                navController = navController
            ) {
                viewModel.idx =  (it-1)*10
                state.idx = viewModel.idx
                viewModel.resetBooks(viewModel.idx)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsUI(
    modifier: Modifier,
    title: String,
    booksResponse: BooksResponse,
    navController: NavController,
    onNavigate: (Int) -> Unit
) {
    val items = booksResponse.items
    val lazyGridState = rememberLazyGridState()

    Scaffold(
        topBar = { Title(title = title, modifier = Modifier.padding(start = 12.dp)) {
            navController.popBackStack()
        } },
        bottomBar = {
            Pagination(size = booksResponse.totalItems/10) {
                onNavigate(it)
            }
        }
    ) { padding ->
        println()
        Column(modifier = modifier.padding(padding)){
            LazyVerticalGrid(state = lazyGridState, modifier = Modifier, columns = GridCells.Adaptive(180.dp), contentPadding = PaddingValues(16.dp)) {
                println(items)
                if (items != null) {
                    items(items.size) {
                        BookCard(modifier = Modifier.padding(6.dp), item = items[it]) {
                            navController.navigate(Screens.DETAIL_SCREEN.name + "/${items[it].id}")
                        }
                    }
                }
            }
        }
    }
}