package my.jalal.bookcatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    var id: String,
    var title: String,
    var posterUrl: String,
    var authors: String,
    var rating: Float,
    var description: String,
    var isFavorite: Boolean
) : Parcelable