package my.jalal.bookcatalogue.core.utils

import java.lang.Exception
import java.net.URL

object UrlChanger {
    fun getHttpsFromHttp(httpUrl: String): String {
        return try {
            val oldUrl = URL(httpUrl)
            val newUrl = URL(
                "https",
                oldUrl.host,
                oldUrl.port,
                oldUrl.file
            )
            newUrl.toString()
        }catch (e: Exception){
            e.toString()
        }
    }
}