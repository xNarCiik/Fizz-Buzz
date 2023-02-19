package com.fizzbuzz.features.fizzbuzz.di

import android.app.Application
import com.fizzbuzz.features.fizzbuzz.presentation.common.FizzBuzzViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFizzBuzzViewModel(app: Application): FizzBuzzViewModel {
        return FizzBuzzViewModel(context = app.applicationContext, dispatcher = Dispatchers.IO)
    }

}