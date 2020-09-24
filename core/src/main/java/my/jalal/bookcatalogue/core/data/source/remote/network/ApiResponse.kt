package my.jalal.bookcatalogue.core.data.source.remote.network

class ApiResponse<out T>(val value: T?, val errorMessage: String)