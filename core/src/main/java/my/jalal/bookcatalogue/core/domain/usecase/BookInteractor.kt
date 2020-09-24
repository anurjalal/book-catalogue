package my.jalal.bookcatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import my.jalal.bookcatalogue.core.data.Resource
import my.jalal.bookcatalogue.core.domain.model.Book
import my.jalal.bookcatalogue.core.domain.repository.IBookRepository

class BookInteractor(private val bookRepository: IBookRepository) : BookUseCase {
    override suspend fun getBookBySearching(keyword: String?): LiveData<Resource<List<Book>>> =  bookRepository.getBookBySearching(keyword)

    override fun getFavoriteBook(): Flow<List<Book>> = bookRepository.getFavoriteBook()
    override fun isBookFavorite(id: String): Flow<Int> = bookRepository.isBookFavorite(id)
    override fun setFavoriteBook(book: Book, state: Boolean) = bookRepository.setFavoriteBook(book, state)

}