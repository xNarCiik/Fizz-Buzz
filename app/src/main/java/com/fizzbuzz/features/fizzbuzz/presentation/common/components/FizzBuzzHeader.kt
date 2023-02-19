package com.fizzbuzz.features.fizzbuzz.presentation.common.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fizzbuzz.R

// Composable representation of the Header of the app
// It's only a string but can be replaced for a better UI
@Composable
fun FizzBuzzHeader() {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.h2
    )
}

@Preview
@Composable
fun FizzBuzzPreview() {
    FizzBuzzHeader()
}