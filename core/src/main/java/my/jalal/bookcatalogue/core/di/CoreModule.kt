package my.jalal.bookcatalogue.core.di

import androidx.room.Room
import my.jalal.bookcatalogue.core.data.source.BookRepository
import my.jalal.bookcatalogue.core.data.source.local.LocalDataSource
import my.jalal.bookcatalogue.core.data.source.local.room.BookDatabase
import my.jalal.bookcatalogue.core.data.source.remote.RemoteDataSource
import my.jalal.bookcatalogue.core.data.source.remote.network.ApiService
import my.jalal.bookcatalogue.core.domain.repository.IBookRepository
import my.jalal.bookcatalogue.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<BookDatabase>().bookDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            BookDatabase::class.java, "Book.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IBookRepository> {
        BookRepository(
            get(),
            get(),
            get()
        )
    }
}