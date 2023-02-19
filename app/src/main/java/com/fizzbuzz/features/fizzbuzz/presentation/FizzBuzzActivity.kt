package com.fizzbuzz.features.fizzbuzz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fizzbuzz.features.fizzbuzz.presentation.common.FizzBuzzViewModel
import com.fizzbuzz.features.fizzbuzz.presentation.form.FormScreen
import com.fizzbuzz.features.fizzbuzz.presentation.result.ResultScreen
import com.fizzbuzz.features.fizzbuzz.presentation.util.Screen
import com.fizzbuzz.ui.theme.FizzBuzzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FizzBuzzActivity : ComponentActivity() {

    private val fizzBuzzViewModel: FizzBuzzViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FizzBuzzTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(all = 22.dp)
                ) {
                    // Instantiate the navigation
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.FormScreen.route
                    ) {
                        composable(route = Screen.FormScreen.route) {
                            FormScreen(navController, fizzBuzzViewModel)
                        }
                        composable(route = Screen.ResultScreen.route) {
                            ResultScreen(navController, fizzBuzzViewModel)
                        }
                    }
                }
            }
        }
    }
}
