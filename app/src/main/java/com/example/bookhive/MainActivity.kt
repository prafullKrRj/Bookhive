package com.example.bookhive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookhive.ui.screens.main.MainScreen
import com.example.bookhive.ui.theme.BookHiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookHiveTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val startDestination = Screens.MAIN_SCREEN.name

                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(route = Screens.MAIN_SCREEN.name) {
                            MainScreen(navController)
                        }
                        composable(route = Screens.SEARCH_SCREEN.name) {
                            
                        }
                        composable(route = Screens.DETAIL_SCREEN.name) {

                        }
                        composable(route = Screens.SEE_MORE_SCREEN.name + "/{bookType}") { navBackStackEntry ->
                            navBackStackEntry.arguments?.getString("bookType").let {

                            }
                        }
                    }
                }
            }
        }
    }
}
enum class Screens {
    MAIN_SCREEN, SEARCH_SCREEN, DETAIL_SCREEN, SEE_MORE_SCREEN
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookHiveTheme {
        Greeting("Android")
    }
}