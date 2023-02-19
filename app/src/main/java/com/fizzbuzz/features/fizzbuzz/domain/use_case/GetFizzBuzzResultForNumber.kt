package com.fizzbuzz.features.fizzbuzz.domain.use_case

class GetFizzBuzzResultForNumber {

    // This use case calculate the result of Fizz-Buzz for a specif number
    // take parameters firstNumber / secondNumber / firstWord / secondWord of the form
    // and the number who want calculate
    // Return the Fizz-Buzz result in String
    operator fun invoke(
        number: Int,
        firstNumber: Int,
        secondNumber: Int,
        firstWord: String,
        secondWord: String
    ): String {
        val isMultipleOfFirstNumber = number % firstNumber == 0
        val isMultipleOfSecondNumber = number % secondNumber == 0

        return when {
            isMultipleOfFirstNumber && isMultipleOfSecondNumber -> "$firstWord$secondWord"
            isMultipleOfFirstNumber -> firstWord
            isMultipleOfSecondNumber -> secondWord
            else -> number.toString()
        }
    }

}