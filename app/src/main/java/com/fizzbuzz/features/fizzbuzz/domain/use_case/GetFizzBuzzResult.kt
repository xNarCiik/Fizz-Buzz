package com.fizzbuzz.features.fizzbuzz.domain.use_case

class GetFizzBuzzResult {

    // This use case calculate the result of Fizz-Buzz
    // take all parameters of the form : firstNumber / secondNumber / firstWord / secondWord and limit
    // Return a array of String
    operator fun invoke(
        firstNumber: Int,
        secondNumber: Int,
        firstWord: String,
        secondWord: String,
        limit: Int
    ): Array<String> {
        val result = arrayListOf<String>()
        for (number in 1..limit) {
            result.add(
                GetFizzBuzzResultForNumber().invoke(
                    number = number,
                    firstNumber = firstNumber,
                    secondNumber = secondNumber,
                    firstWord = firstWord,
                    secondWord = secondWord
                )
            )
        }
        return result.toTypedArray()
    }

}