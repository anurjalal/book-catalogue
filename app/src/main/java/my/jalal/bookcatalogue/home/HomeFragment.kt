package my.jalal.bookcatalogue.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import my.jalal.bookcatalogue.R
import my.jalal.bookcatalogue.core.ui.BookAdapter
import my.jalal.bookcatalogue.detail.DetailBookActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookAdapter = BookAdapter()
        bookAdapter.onItemCLick = { selectedData ->
            val intent = Intent(activity, DetailBookActivity::class.java)
            intent.putExtra(DetailBookActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        sv_book.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(charSeq: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSeq: CharSequence?, p1: Int, p2: Int, p3: Int) {
                homeViewModel.getBook(charSeq.toString())
                progress_bar.visibility = View.VISIBLE
            }
        })
        homeViewModel.data.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = View.GONE
            bookAdapter.setData(it?.data)
        })

        with(rv_book) {
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
