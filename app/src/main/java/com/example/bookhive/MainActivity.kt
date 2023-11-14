package com.example.bookhive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookhive.ui.screens.details.DetailScreen
import com.example.bookhive.ui.screens.details.DetailViewModel
import com.example.bookhive.ui.screens.main.MainScreen
import com.example.bookhive.ui.screens.main.MainScreenViewModel
import com.example.bookhive.ui.screens.more.MoreScreen
import com.example.bookhive.ui.screens.more.MoreViewModel
import com.example.bookhive.ui.screens.search.SearchScreen
import com.example.bookhive.ui.screens.search.SearchViewModel
import com.example.bookhive.ui.screens.search.SearchResultsScreen
import com.example.bookhive.ui.theme.BookHiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BookHiveTheme () {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val startDestination = Screens.MAIN_SCREEN.name

                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(route = Screens.MAIN_SCREEN.name) {
                            val viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
                            MainScreen(viewModel = viewModel, navController = navController) {
                                viewModel.retryStart()
                            }
                        }
                        composable(route = Screens.SEARCH_SCREEN.name) {
                            val x = object {
                                val Factory: ViewModelProvider.Factory = viewModelFactory {
                                    initializer {
                                        val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookApplication)
                                        val booksRepository = application.container.bookDetailRepository
                                        SearchViewModel(booksRepository = booksRepository)
                                    }
                                }
                            }
                            val viewModel: SearchViewModel = viewModel(factory = x.Factory)
                            SearchScreen(viewModel = viewModel, navController = navController)
                        }
                        composable(route = Screens.DETAIL_SCREEN.name + "/{bookName}") {navBackStackEntry ->
                            navBackStackEntry.arguments?.getString("bookName").let {
                                it?.let { it1 ->
                                    val x = object {
                                        val Factory: ViewModelProvider.Factory = viewModelFactory {
                                            initializer {
                                                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookApplication)
                                                val booksRepository = application.container.bookDetailRepository
                                                DetailViewModel(booksRepository = booksRepository, bookId = it1)
                                            }
                                        }
                                    }
                                    val viewModel: DetailViewModel = viewModel(factory = x.Factory)
                                    DetailScreen(navController = navController, txt = it1, viewModel = viewModel, onRetry = {
                                        viewModel.retryStart()
                                    })
                                }
                            }
                        }
                        composable(route = Screens.SEE_MORE_SCREEN.name + "/{bookType}") { navBackStackEntry ->
                            navBackStackEntry.arguments?.getString("bookType").let {

                                it?.let { it1 ->
                                    val x = object {
                                        val Factory: ViewModelProvider.Factory = viewModelFactory {
                                            initializer {
                                                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookApplication)
                                                val booksRepository = application.container.bookDetailRepository
                                                MoreViewModel(booksRepository = booksRepository, query = it1)
                                            }
                                        }
                                    }
                                    val viewModel: MoreViewModel = viewModel(factory = x.Factory)
                                    MoreScreen(it1, navController, viewModel = viewModel, onRetry = {
                                        viewModel.retry()
                                    })
                                }
                            }
                        }
                        composable(route = Screens.SEARCH_RESULTS_SCREEN.name + "/{query}") {navBackStackEntry->


                         navBackStackEntry.arguments?.getString("query").let {

                                it?.let { it1 ->
                                    val x = object {
                                        val Factory: ViewModelProvider.Factory = viewModelFactory {
                                            initializer {
                                                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookApplication)
                                                val booksRepository = application.container.bookDetailRepository
                                                SearchViewModel(booksRepository = booksRepository)
                                            }
                                        }
                                    }
                                    val viewModel: SearchViewModel = viewModel(factory = x.Factory)
                                    viewModel.getBooks(it1, viewModel.idx)
                                    SearchResultsScreen(viewModel = viewModel, navController = navController, onRetry = {
                                        viewModel.getBooks(it1, viewModel.idx)
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



enum class Screens {
    MAIN_SCREEN, SEARCH_SCREEN, DETAIL_SCREEN, SEE_MORE_SCREEN, SEARCH_RESULTS_SCREEN
}
