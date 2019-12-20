package com.rapider.di

import android.content.Context
import com.rapider.AndroidApplication
import com.rapider.utils.CustomLogger
import com.rapider.utils.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideLogger():Logger{
        return CustomLogger()
    }
}
