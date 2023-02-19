package com.fizzbuzz.features.fizzbuzz.presentation.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fizzbuzz.R
import com.fizzbuzz.features.fizzbuzz.presentation.common.FizzBuzzViewModel
import com.fizzbuzz.features.fizzbuzz.presentation.common.components.FizzBuzzButton
import com.fizzbuzz.features.fizzbuzz.presentation.common.components.FizzBuzzHeader
import com.fizzbuzz.features.fizzbuzz.presentation.form.components.FizzBuzzTextFieldWithError
import com.fizzbuzz.features.fizzbuzz.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

// Composable representation of the form screen
@Composable
fun FormScreen(
    navController: NavController,
    fizzBuzzViewModel: FizzBuzzViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FizzBuzzHeader()

        FormComponent(navController, fizzBuzzViewModel)
    }
}

@Composable
fun FormComponent(
    navController: NavController,
    fizzBuzzViewModel: FizzBuzzViewModel
) {
    // MutableState
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var firstWord by remember { mutableStateOf("") }
    var secondWord by remember { mutableStateOf("") }
    var limit by remember { mutableStateOf("") }

    // ViewModelState
    val firstNumberError by fizzBuzzViewModel.firstNumberError.collectAsState()
    val secondNumberError by fizzBuzzViewModel.secondNumberError.collectAsState()
    val firstWordError by fizzBuzzViewModel.firstWordError.collectAsState()
    val secondWordError by fizzBuzzViewModel.secondWordError.collectAsState()
    val limitError by fizzBuzzViewModel.limitError.collectAsState()

    // when we received a result from the view model => navigate to the result screen
    // Do it in LaunchedEffect to avoid infinite navigate
    LaunchedEffect(Unit) {
        fizzBuzzViewModel.fizzBuzzResult.collectLatest { fizzBuzzResult ->
            if (fizzBuzzResult.isNotEmpty()) {
                navController.navigate(Screen.ResultScreen.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.form),
            style = MaterialTheme.typography.h4
        )

        Spacer(Modifier.height(22.dp))

        FizzBuzzTextFieldWithError(
            value = firstNumber,
            onValueChange = { firstNumber = it },
            hintResId = R.string.first_number,
            keyboardType = KeyboardType.Number,
            error = firstNumberError
        )

        FizzBuzzTextFieldWithError(
            value = secondNumber,
            onValueChange = { secondNumber = it },
            hintResId = R.string.second_number,
            keyboardType = KeyboardType.Number,
            error = secondNumberError
        )

        Spacer(Modifier.height(10.dp))

        FizzBuzzTextFieldWithError(
            value = firstWord,
            onValueChange = { firstWord = it },
            hintResId = R.string.first_word,
            error = firstWordError
        )

        FizzBuzzTextFieldWithError(
            value = secondWord,
            onValueChange = { secondWord = it },
            hintResId = R.string.second_word,
            error = secondWordError
        )

        Spacer(Modifier.height(10.dp))

        FizzBuzzTextFieldWithError(
            value = limit,
            onValueChange = { limit = it },
            hintResId = R.string.limit,
            keyboardType = KeyboardType.Number,
            error = limitError
        )

        Spacer(Modifier.weight(1f))

        FizzBuzzButton(
            onClick = {
                fizzBuzzViewModel.onValidateButtonClicked(
                    firstNumberString = firstNumber,
                    secondNumberString = secondNumber,
                    firstWord = firstWord,
                    secondWord = secondWord,
                    limitString = limit
                )
            },
            text = stringResource(R.string.validate)
        )
    }
}

@Preview
@Composable
fun FormScreenPreview() {
    val navController = rememberNavController()
    FormScreen(navController = navController, fizzBuzzViewModel = hiltViewModel())
}