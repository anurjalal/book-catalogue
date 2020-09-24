package my.jalal.bookcatalogue.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import my.jalal.bookcatalogue.core.domain.usecase.BookUseCase

class FavoriteViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val favoriteBook = bookUseCase.getFavoriteBook().asLiveData()
}
