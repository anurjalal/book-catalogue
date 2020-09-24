package my.jalal.bookcatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
class BookEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name= "title")
    var title: String,
    @ColumnInfo(name= "poster_url")
    var posterUrl: String,
    @ColumnInfo(name= "authors")
    var authors: String,
    @ColumnInfo(name= "rating")
    var rating: Float,
    @ColumnInfo(name= "description")
    var description: String,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
)