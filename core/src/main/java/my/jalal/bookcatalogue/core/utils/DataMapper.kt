package my.jalal.bookcatalogue.core.utils

import my.jalal.bookcatalogue.core.data.source.local.entity.BookEntity
import my.jalal.bookcatalogue.core.data.source.remote.response.BookResponse
import my.jalal.bookcatalogue.core.domain.model.Book

object DataMapper {
    fun mapResponseToDomain(input: List<BookResponse>): List<Book> {
        val gameList = ArrayList<Book>()

        input.map {
            val game = it.id?.let { it1 ->
                Book(
                    id = it1,
                    title =  it.volumeInfo?.title ?: "",
                    authors = it.volumeInfo?.authors?.joinToString(", ") ?: "",
                    rating = it.volumeInfo?.averageRating ?: 0F,
                    description = it.volumeInfo?.description ?: "",
                    posterUrl = it.volumeInfo?.imageLinks?.thumbnail ?: "",
                    isFavorite = false
                )
            }
            game?.let { it1 -> gameList.add(it1) }
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<BookEntity>): List<Book> =
        input.map {
            Book(
                id = it.id,
                title = it.title,
                authors = it.authors,
                rating = it.rating,
                posterUrl = it.posterUrl,
                description = it.description,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Book): BookEntity {
        return BookEntity(
            id = input.id,
            title = input.title,
            authors = input.authors,
            rating = input.rating,
            posterUrl = input.posterUrl,
            description = input.description,
            isFavorite = input.isFavorite
        )
    }

}
