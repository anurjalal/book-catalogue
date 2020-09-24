package my.jalal.bookcatalogue.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_book.view.*
import my.jalal.bookcatalogue.core.R
import my.jalal.bookcatalogue.core.domain.model.Book
import my.jalal.bookcatalogue.core.utils.UrlChanger

class BookAdapter : RecyclerView.Adapter<BookAdapter.ListViewHolder>() {
    private var listData = ArrayList<Book>()
    var onItemCLick: ((Book) -> Unit)? = null

    fun setData(newListData: List<Book>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Book) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(UrlChanger.getHttpsFromHttp(data.posterUrl))
                    .into(iv_book)
                tv_name.text = data.title
                tv_score.rating = data.rating
                tv_author.text = data.authors
            }
        }

        init {
            itemView.setOnClickListener {
                onItemCLick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_book, parent, false)
    )


    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}