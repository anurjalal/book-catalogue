package my.jalal.bookcatalogue.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_book.*
import my.jalal.bookcatalogue.R
import my.jalal.bookcatalogue.core.domain.model.Book
import my.jalal.bookcatalogue.core.utils.UrlChanger
import org.koin.android.viewmodel.ext.android.viewModel

class DetailBookActivity : AppCompatActivity() {

    private val detailBookViewModel: DetailBookViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_book)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val detailTourism = intent.getParcelableExtra<Book>(EXTRA_DATA)
        Log.d("CEKCEK", detailTourism.toString())
        showDetailTourism(detailTourism)
    }

    private fun showDetailTourism(detailBook: Book?) {
        detailBook?.let {
            supportActionBar?.title = detailBook.title
            tv_name.text = detailBook.title
            Glide.with(this)
                .load(UrlChanger.getHttpsFromHttp(detailBook.posterUrl))
                .into(iv_book)
            tv_score.rating = detailBook.rating
            tv_author.text = detailBook.authors
            tv_description.text = detailBook.description
            var statusFavorite = false
            detailBookViewModel.isFavorite(detailBook.id).observe(this, Observer {
                statusFavorite = if (it == 1) {
                    setStatusFavorite(true)
                    true
                } else {
                    setStatusFavorite(false)
                    false

                }
            })
            iv_favorite.setOnClickListener {
                statusFavorite = !statusFavorite
                showSnackbar(statusFavorite, this.movie_detail_activity)
                detailBookViewModel.setFavoriteTourism(detailBook, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun showSnackbar(statusFavorite: Boolean, view: View) {
        if (statusFavorite) {
            Snackbar.make(
                view,
                resources.getString(R.string.succ_add_fav_book_msg),
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            Snackbar.make(
                view,
                resources.getString(R.string.succ_remove_fav_book_msg),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            iv_favorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_pink24dp
                )
            )
        } else {
            iv_favorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border_pink_24dp
                )
            )
        }
    }
}
