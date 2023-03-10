package com.fizzbuzz.features.fizzbuzz.presentation.form.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Composable representation of TextField
@Composable
fun FizzBuzzTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintValue: String? = null,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center)
    val isFocused = remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(vertical = 4.dp)
            .onFocusChanged {
                isFocused.value = it.isFocused
            },
        placeholder = {
            hintValue?.let {
                if(!isFocused.value){
                    Text(
                        text = it,
                        modifier = Modifier.fillMaxWidth(),
                        style = textStyle
                    )
                }
            }
        },
        shape = RoundedCornerShape(3.dp),
        textStyle = textStyle,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview
@Composable
fun FizzBuzzTextFieldPreview() {
    FizzBuzzTextField(value = "Value", onValueChange = {})
}