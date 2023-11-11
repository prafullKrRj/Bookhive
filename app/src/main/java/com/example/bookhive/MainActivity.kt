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
import com.example.bookhive.ui.screens.more.MoreScreen
import com.example.bookhive.ui.screens.more.MoreViewModel
import com.example.bookhive.ui.screens.search.SearchScreen
import com.example.bookhive.ui.screens.search.SearchViewModel
import com.example.bookhive.ui.screens.search.components.SearchResultsScreen
import com.example.bookhive.ui.theme.BookHiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          /*  val preferences = getSharedPreferences("Theme", MODE_PRIVATE)
            val isDark = preferences.getBoolean("isDark", true)
            val theme = rememberSaveable {
                mutableStateOf(preferences.getBoolean("isDark", true))
            }*/
            BookHiveTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val startDestination = Screens.MAIN_SCREEN.name

                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(route = Screens.MAIN_SCREEN.name) {
                            MainScreen(navController) {
                           //     preferences.edit().putBoolean("isDark", !isDark).apply()
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
                            SearchScreen(viewModel = viewModel(factory = x.Factory), navController = navController)
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
                                    DetailScreen(navController = navController, txt = it1, viewModel = viewModel(factory = x.Factory))
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
                                    MoreScreen(it1, navController, viewModel = viewModel(factory = x.Factory))
                                }
                            }
                        }
                        composable(route = Screens.SEARCH_RESULTS_SCREEN.name) {
                            SearchResultsScreen()
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
