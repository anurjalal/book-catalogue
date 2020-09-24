package my.jalal.bookcatalogue.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import my.jalal.bookcatalogue.core.data.source.local.entity.BookEntity

@Dao
interface BookDao {
    @Query("SELECT * FROM book WHERE is_favorite=1")
    fun getFavoriteBook(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoriteBook(book: BookEntity)

    @Query("SELECT is_favorite FROM book WHERE id=:id")
    fun isBookFavorite(id: String): Flow<Int>
}