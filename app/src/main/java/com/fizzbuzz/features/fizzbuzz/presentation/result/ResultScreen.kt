package com.fizzbuzz.features.fizzbuzz.presentation.result

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fizzbuzz.R
import com.fizzbuzz.features.fizzbuzz.presentation.common.FizzBuzzViewModel
import com.fizzbuzz.features.fizzbuzz.presentation.common.components.FizzBuzzButton
import com.fizzbuzz.features.fizzbuzz.presentation.common.components.FizzBuzzHeader

// Composable representation of the result screen
@Composable
fun ResultScreen(
    navController: NavController,
    fizzBuzzViewModel: FizzBuzzViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FizzBuzzHeader()

        ResultComponent(navController, fizzBuzzViewModel)
    }
}

@Composable
fun ResultComponent(
    navController: NavController,
    fizzBuzzViewModel: FizzBuzzViewModel
) {
    val fizzBuzzResult by fizzBuzzViewModel.fizzBuzzResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.result),
            style = MaterialTheme.typography.h4
        )

        Spacer(Modifier.height(22.dp))

        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 22.dp)
        ) {
            itemsIndexed(
                items = fizzBuzzResult
            ) { index, data ->
                Text(
                    text = "${index + 1}. $data"
                )
            }
        }

        Spacer(Modifier.height(22.dp))

        FizzBuzzButton(
            onClick = {
                fizzBuzzViewModel.onBackButtonClicked()
                navController.popBackStack()
            },
            text = stringResource(R.string.back)
        )
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    val navController = rememberNavController()
    ResultScreen(navController = navController, fizzBuzzViewModel = hiltViewModel())
}