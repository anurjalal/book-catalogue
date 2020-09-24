package my.jalal.bookcatalogue.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import my.jalal.bookcatalogue.core.data.Resource
import my.jalal.bookcatalogue.core.domain.model.Book
import my.jalal.bookcatalogue.core.domain.usecase.BookUseCase

class HomeViewModel(private val bookUseCase: BookUseCase) : ViewModel() {
    private val _data = MutableLiveData<Resource<List<Book>>>()
    val data: LiveData<Resource<List<Book>>>
        get() = _data

    fun getBook(keywords: String) {
        viewModelScope.launch {
            val responseDeffered = async { bookUseCase.getBookBySearching(keywords) }
            val bookResponse = responseDeffered.await()
            _data.value = bookResponse.value
            Log.d("BEBEBEB", _data.value?.data?.get(0)?.posterUrl.toString())
        }
    }
}
