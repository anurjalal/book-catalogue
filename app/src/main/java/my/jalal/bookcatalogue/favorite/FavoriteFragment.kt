package my.jalal.bookcatalogue.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import my.jalal.bookcatalogue.detail.DetailBookActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import my.jalal.bookcatalogue.R
import my.jalal.bookcatalogue.core.ui.BookAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val bookAdapter = BookAdapter()
            bookAdapter.onItemCLick = { selectedData ->
                val intent = Intent(activity, DetailBookActivity::class.java)
                intent.putExtra(DetailBookActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteBook.observe(viewLifecycleOwner, Observer { favoriteBook ->
                bookAdapter.setData(favoriteBook)
                view_empty.visibility = if (favoriteBook.isNotEmpty()) View.GONE else View.VISIBLE

            })

            with(rv_favorite_game) {
                layoutManager = LinearLayoutManager(context)
                this.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                setHasFixedSize(false)
                adapter = bookAdapter
            }
        }
    }
}
