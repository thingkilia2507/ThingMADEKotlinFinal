package com.thing.bangkit.thingjetpackkotlin

import android.app.Application
import com.thing.bangkit.thingjetpackkotlin.core.di.databaseModule
import com.thing.bangkit.thingjetpackkotlin.core.di.networkModule
import com.thing.bangkit.thingjetpackkotlin.core.di.repositoryModule
import com.thing.bangkit.thingjetpackkotlin.di.useCaseModule
import com.thing.bangkit.thingjetpackkotlin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication  : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}