package com.fizzbuzz.features.fizzbuzz.domain.use_case

import org.junit.Test

import org.junit.Assert.*

class GetFizzBuzzResultForNumberTest {

    @Test
    fun getFizzBuzzResultIsCorrect() {
        val getFizzBuzzResultForNumber = GetFizzBuzzResultForNumber()
        val result = getFizzBuzzResultForNumber.invoke(
            number = 1,
            firstNumber = 2,
            secondNumber = 4,
            firstWord = "first",
            secondWord = "second"
        )
        val result2 = getFizzBuzzResultForNumber.invoke(
            number = 2,
            firstNumber = 2,
            secondNumber = 4,
            firstWord = "first",
            secondWord = "second"
        )
        val result4 = getFizzBuzzResultForNumber.invoke(
            number = 4,
            firstNumber = 2,
            secondNumber = 4,
            firstWord = "first",
            secondWord = "second"
        )
        assertEquals(result, "1")
        assertEquals(result2, "first")
        assertEquals(result4, "firstsecond")
    }

}