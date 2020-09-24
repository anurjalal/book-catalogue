package my.jalal.bookcatalogue

import android.app.Application
import my.jalal.bookcatalogue.core.di.databaseModule
import my.jalal.bookcatalogue.core.di.networkModule
import my.jalal.bookcatalogue.core.di.repositoryModule
import my.jalal.bookcatalogue.di.useCaseModule
import my.jalal.bookcatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(org.koin.core.logger.Level.NONE)
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