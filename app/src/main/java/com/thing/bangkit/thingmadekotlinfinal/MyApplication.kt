package com.thing.bangkit.thingmadekotlinfinal

import android.app.Application
import com.thing.bangkit.thingmadekotlinfinal.core.di.databaseModule
import com.thing.bangkit.thingmadekotlinfinal.core.di.networkModule
import com.thing.bangkit.thingmadekotlinfinal.core.di.repositoryModule
import com.thing.bangkit.thingmadekotlinfinal.di.useCaseModule
import com.thing.bangkit.thingmadekotlinfinal.di.viewModelModule
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