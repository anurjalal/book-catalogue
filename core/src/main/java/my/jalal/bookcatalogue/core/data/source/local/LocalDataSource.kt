package my.jalal.bookcatalogue.core.data.source.local

import kotlinx.coroutines.flow.Flow
import my.jalal.bookcatalogue.core.data.source.local.entity.BookEntity
import my.jalal.bookcatalogue.core.data.source.local.room.BookDao

class LocalDataSource(private val bookDao: BookDao) {
    fun getFavoriteBook(): Flow<List<BookEntity>> = bookDao.getFavoriteBook()
    fun setFavoriteBook(book: BookEntity, newState: Boolean) {
        book.isFavorite = newState
        bookDao.updateFavoriteBook(book)
    }

    fun isBookFavorite(id: String): Flow<Int> = bookDao.isBookFavorite(id)
}