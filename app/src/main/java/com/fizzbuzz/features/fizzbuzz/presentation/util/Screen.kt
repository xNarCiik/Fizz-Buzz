package com.fizzbuzz.features.fizzbuzz.presentation.util

// This class is used for the navigation
// Each object represent a screen
sealed class Screen(val route: String) {
    object FormScreen: Screen("form_screen")
    object ResultScreen: Screen("result_screen")
}
