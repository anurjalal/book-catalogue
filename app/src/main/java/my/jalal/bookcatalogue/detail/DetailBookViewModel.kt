package my.jalal.bookcatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import my.jalal.bookcatalogue.core.domain.model.Book
import my.jalal.bookcatalogue.core.domain.usecase.BookUseCase

class DetailBookViewModel(private val bookUseCase: BookUseCase) : ViewModel() {
    fun setFavoriteTourism(book: Book, newStatus:Boolean) =
        bookUseCase.setFavoriteBook(book, newStatus)

    fun isFavorite(id: String): LiveData<Int> {
        return bookUseCase.isBookFavorite(id).asLiveData()
    }
}
