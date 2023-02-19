package com.fizzbuzz.features.fizzbuzz.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Composable representation of Button
@Composable
fun FizzBuzzButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    OutlinedButton(
        modifier = modifier.fillMaxSize(),
        onClick = onClick,
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        content = {
            Text(
                text = text.toUpperCase(Locale.current),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@Preview
@Composable
fun FizzBuzzButtonPreview() {
    FizzBuzzButton(onClick = {}, text = "button")
}