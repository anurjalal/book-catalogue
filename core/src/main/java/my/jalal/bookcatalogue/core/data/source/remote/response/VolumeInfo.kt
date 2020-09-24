package my.jalal.bookcatalogue.core.data.source.remote.response

data class VolumeInfo(
    val authors: List<String>?,
    val averageRating: Float?,
    val description: String?,
    val imageLinks: ImageLinks?,
    val title: String?
)