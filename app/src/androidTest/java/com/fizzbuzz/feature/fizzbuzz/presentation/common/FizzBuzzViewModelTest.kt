package com.fizzbuzz.feature.fizzbuzz.presentation.common

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fizzbuzz.features.fizzbuzz.presentation.common.FizzBuzzViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class FizzBuzzViewModelTest {

    private lateinit var viewModel: FizzBuzzViewModel

    @Before
    fun setUp() {
        viewModel = FizzBuzzViewModel(
            context = InstrumentationRegistry
                .getInstrumentation().targetContext,
            dispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun wrongValueShouldEmitErrorsTest() {
        viewModel.apply {
            // Test error one by one
            onValidateButtonClicked(
                firstNumberString = "",
                secondNumberString = "1",
                firstWord = "a",
                secondWord = "a",
                limitString = "1"
            )

            assert(firstNumberError.value.isNotEmpty())
            assert(firstNumberError.value.isNotEmpty())
            assert(secondNumberError.value.isEmpty())
            assert(firstWordError.value.isEmpty())
            assert(secondWordError.value.isEmpty())
            assert(limitError.value.isEmpty())

            onValidateButtonClicked(
                firstNumberString = "1",
                secondNumberString = "",
                firstWord = "a",
                secondWord = "a",
                limitString = "1"
            )
            assert(firstNumberError.value.isEmpty())
            assert(secondNumberError.value.isNotEmpty())
            assert(firstWordError.value.isEmpty())
            assert(secondWordError.value.isEmpty())
            assert(limitError.value.isEmpty())

            onValidateButtonClicked(
                firstNumberString = "1",
                secondNumberString = "1",
                firstWord = "",
                secondWord = "a",
                limitString = "1"
            )
            assert(firstNumberError.value.isEmpty())
            assert(secondNumberError.value.isEmpty())
            assert(firstWordError.value.isNotEmpty())
            assert(secondWordError.value.isEmpty())
            assert(limitError.value.isEmpty())

            onValidateButtonClicked(
                firstNumberString = "1",
                secondNumberString = "1",
                firstWord = "a",
                secondWord = "",
                limitString = "1"
            )
            assert(firstNumberError.value.isEmpty())
            assert(secondNumberError.value.isEmpty())
            assert(firstWordError.value.isEmpty())
            assert(secondWordError.value.isNotEmpty())
            assert(limitError.value.isEmpty())

            onValidateButtonClicked(
                firstNumberString = "1",
                secondNumberString = "1",
                firstWord = "a",
                secondWord = "1",
                limitString = ""
            )
            assert(firstNumberError.value.isEmpty())
            assert(secondNumberError.value.isEmpty())
            assert(firstWordError.value.isEmpty())
            assert(secondWordError.value.isEmpty())
            assert(limitError.value.isNotEmpty())
        }
    }

    @Test
    fun goodValuesShouldEmitResultTest() = runTest(UnconfinedTestDispatcher()) {
        viewModel.onValidateButtonClicked(
            firstNumberString = "2",
            secondNumberString = "4",
            firstWord = "a",
            secondWord = "b",
            limitString = "4"
        )
        advanceUntilIdle()
        assertArrayEquals(viewModel.fizzBuzzResult.value, arrayOf("1", "a", "3", "ab"))
    }
}