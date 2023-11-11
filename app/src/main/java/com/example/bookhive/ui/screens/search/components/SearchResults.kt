package com.example.bookhive.ui.screens.search.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.bookhive.ui.screens.commons.Pagination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(){
    Scaffold(
        bottomBar = {
            Pagination(size = 123) {

            }
        },
    ) {  paddingValues ->
        LazyColumn(contentPadding = paddingValues) {

        }
    }
}