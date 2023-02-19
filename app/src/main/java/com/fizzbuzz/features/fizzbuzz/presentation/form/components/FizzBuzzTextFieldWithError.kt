package com.fizzbuzz.features.fizzbuzz.presentation.form.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.fizzbuzz.R

// Composable representation of TextField with a error text
@Composable
fun FizzBuzzTextFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    hintResId: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    error: String
) {
    FizzBuzzTextField(
        modifier = Modifier.fillMaxSize(),
        value = value,
        onValueChange = onValueChange,
        hintValue = stringResource(hintResId),
        keyboardType = keyboardType
    )

    if (error.isNotEmpty()) {
        Text(text = error, color = Color.Red)
    }
}

@Preview
@Composable
fun FizzBuzzTextFieldWithErrorPreview() {
    FizzBuzzTextFieldWithError(
        value = "Value",
        onValueChange = {},
        hintResId = R.string.app_name,
        error = "error"
    )
}