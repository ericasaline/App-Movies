package com.app.app.application

import android.app.Application
import com.app.app.modules.repositoryModule
import com.app.app.modules.retrofitModule
import com.app.app.modules.serviceModule
import com.app.app.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    retrofitModule,
                    serviceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}