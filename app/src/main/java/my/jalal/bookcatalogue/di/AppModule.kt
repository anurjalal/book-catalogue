package my.jalal.bookcatalogue.di

import my.jalal.bookcatalogue.core.domain.usecase.BookInteractor
import my.jalal.bookcatalogue.core.domain.usecase.BookUseCase
import my.jalal.bookcatalogue.detail.DetailBookViewModel
import my.jalal.bookcatalogue.favorite.FavoriteViewModel
import my.jalal.bookcatalogue.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BookUseCase> { BookInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailBookViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}