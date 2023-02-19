package com.fizzbuzz.features.fizzbuzz.domain.use_case

import org.junit.Test

import org.junit.Assert.*

class GetFizzBuzzResultTest {

    @Test
    fun getFizzBuzzResultIsCorrect() {
        val getFizzBuzzResult = GetFizzBuzzResult()
        val result = getFizzBuzzResult.invoke(
            firstNumber = 2,
            secondNumber = 5,
            firstWord = "first",
            secondWord = "second",
            10
        )
        val expectedResult = arrayOf("1", "first", "3", "first", "second", "first", "7", "first", "9", "firstsecond")
        assertArrayEquals(expectedResult, result)
    }

    @Test
    fun getFizzBuzzResultLargeLimitTest() {
        val getFizzBuzzResult = GetFizzBuzzResult()

        // test with only first number valid to create a expected result more easily
        val result = getFizzBuzzResult.invoke(
            firstNumber = 1,
            secondNumber = 1000001,
            firstWord = "first",
            secondWord = "second",
            1000000
        )
        val expectedResult = Array(1000000) { "first" }
        assertArrayEquals(expectedResult, result)
    }

}