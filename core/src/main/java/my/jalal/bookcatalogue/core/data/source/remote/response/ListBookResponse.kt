package my.jalal.bookcatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListBookResponse(
    @field:SerializedName("items")
    val bookResponse: List<BookResponse>?,
    val kind: String?,
    val totalItems: Int?
)
