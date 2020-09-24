package my.jalal.bookcatalogue.core.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import my.jalal.bookcatalogue.core.data.Resource
import my.jalal.bookcatalogue.core.data.source.local.LocalDataSource
import my.jalal.bookcatalogue.core.data.source.remote.RemoteDataSource
import my.jalal.bookcatalogue.core.domain.model.Book
import my.jalal.bookcatalogue.core.domain.repository.IBookRepository
import my.jalal.bookcatalogue.core.utils.AppExecutors
import my.jalal.bookcatalogue.core.utils.DataMapper

class BookRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {
    override suspend fun getBookBySearching(keyword: String?): LiveData<Resource<List<Book>>> {
        val result: MutableLiveData<Resource<List<Book>>> = MutableLiveData()
        val list = runCatching { remoteDataSource.getBookBySearching(keyword) }
        list.onSuccess {
            result.value = Resource(it.value?.let { it1 -> DataMapper.mapResponseToDomain(it1) })
        }.onFailure {
            result.value = Resource(null, it.message)
        }
        return result
    }

    override fun getFavoriteBook(): Flow<List<Book>> = localDataSource.getFavoriteBook().map {
        DataMapper.mapEntitiesToDomain(it)
    }

    override fun setFavoriteBook(book: Book, state:  Boolean) {
        val bookEntity = DataMapper.mapDomainToEntity(book)
        Log.d("TAGTAG", state.toString())
        appExecutors.diskIO().execute {
           val a =  localDataSource.setFavoriteBook(bookEntity, state)
            Log.d("ztatuz", a.toString())
        }
    }

    override fun isBookFavorite(id: String): Flow<Int>  = localDataSource.isBookFavorite(id)
}
