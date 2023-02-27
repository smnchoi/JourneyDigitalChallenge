package com.example.journeydigitalchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.journeydigitalchallenge.ui.theme.JourneyDigitalChallengeTheme
import com.example.journeydigitalchallenge.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.Black.toArgb()

        setContent {
            val viewModel: MainViewModel = viewModel()
            val navController = rememberNavController()

            JourneyDigitalChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(navController = navController, startDestination = "mainScreen") {
                        composable("mainScreen") {
                            MainScreen(viewModel, navController)
                        }
                        composable(
                            "postScreen/{postId}",
                            arguments = listOf(navArgument("postId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val postId = backStackEntry.arguments?.getInt("postId") ?: -1
                            val post = viewModel.getPostById(postId)
                            if (post != null) {
                                PostScreen(post = post)
                            }
                        }
                    }
                }
            }
        }
    }
}

