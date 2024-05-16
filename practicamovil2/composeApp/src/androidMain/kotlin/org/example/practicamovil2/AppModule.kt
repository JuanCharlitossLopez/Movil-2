package org.example.practicamovil2

import android.app.Application
import com.jetbrains.spacetutorial.SpaceXSDK
import com.jetbrains.spacetutorial.cache.AndroidDatabaseDriverFactory
import com.jetbrains.spacetutorial.network.SpaceXApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

val appModule = module {
    single<SpaceXApi> { SpaceXApi() }
    single<SpaceXSDK> {
        SpaceXSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(
                androidContext()
            ), api = get()
        )
    }
    viewModel { RocketLaunchViewModel(sdk = get()) }
}

