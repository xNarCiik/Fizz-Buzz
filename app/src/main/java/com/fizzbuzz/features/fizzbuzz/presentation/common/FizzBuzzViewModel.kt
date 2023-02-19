package com.fizzbuzz.features.fizzbuzz.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fizzbuzz.R
import com.fizzbuzz.features.fizzbuzz.domain.use_case.GetFizzBuzzResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FizzBuzzViewModel @Inject constructor(
    context: Context,
    val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _firstNumberError = MutableStateFlow("")
    val firstNumberError: StateFlow<String> = _firstNumberError

    private val _secondNumberError = MutableStateFlow("")
    val secondNumberError: StateFlow<String> = _secondNumberError

    private val _firstWordError = MutableStateFlow("")
    val firstWordError: StateFlow<String> = _firstWordError

    private val _secondWordError = MutableStateFlow("")
    val secondWordError: StateFlow<String> = _secondWordError

    private val _limitError = MutableStateFlow("")
    val limitError: StateFlow<String> = _limitError

    private val _fizzBuzzResult = MutableStateFlow(arrayOf<String>())
    val fizzBuzzResult: StateFlow<Array<String>> = _fizzBuzzResult

    private val errorString = context.getString(R.string.form_error)

    // On validate button clicked :
    // Check all parameters is correct or not
    // Calculate Fizz-Buzz result
    fun onValidateButtonClicked(
        firstNumberString: String,
        secondNumberString: String,
        firstWord: String,
        secondWord: String,
        limitString: String
    ) {
        viewModelScope.launch(dispatcher) {
            val firstNumber = firstNumberString.toIntOrNull()
            val secondNumber = secondNumberString.toIntOrNull()
            val limit = limitString.toIntOrNull()

            // Check all parameters
            var hasError = checkNumberValue(firstNumber, _firstNumberError)
            hasError = checkNumberValue(firstNumber, _firstNumberError) || hasError
            hasError = checkNumberValue(secondNumber, _secondNumberError) || hasError
            hasError = checkTextValue(firstWord, _firstWordError) || hasError
            hasError = checkTextValue(secondWord, _secondWordError) || hasError
            hasError = checkNumberValue(limit, _limitError, 1000000) || hasError

            if (!hasError) {
                // Calculated only if we dont have error. In IO thread to avoid long calculation problem
                firstNumber?.let {
                    secondNumber?.let {
                        limit?.let {
                            _fizzBuzzResult.emit(
                                GetFizzBuzzResult().invoke(
                                    firstNumber = firstNumber,
                                    secondNumber = secondNumber,
                                    firstWord = firstWord,
                                    secondWord = secondWord,
                                    limit = limit
                                )
                            )
                        }
                    }
                }

            }
        }
    }

    // On back button clicked => reset all data
    fun onBackButtonClicked() {
        _firstNumberError.value = ""
        _secondNumberError.value = ""
        _firstWordError.value = ""
        _secondWordError.value = ""
        _limitError.value = ""
        _fizzBuzzResult.value = arrayOf()
    }

    // Post error if value is null or <= 0
    // Return true if have error
    private fun checkNumberValue(value: Int?, error: MutableStateFlow<String>, maxValue: Int? = null): Boolean {
        return when {
            value == null || value <= 0 || (maxValue != null && maxValue < value) -> {
                error.value = errorString
                true
            }
            else -> {
                error.value = ""
                false
            }
        }
    }

    // Post error if value is empty
    // Return true if have error
    private fun checkTextValue(value: String, error: MutableStateFlow<String>): Boolean {
        return if (value.isEmpty()) {
            error.value = errorString
            true
        } else {
            error.value = ""
            false
        }
    }

}