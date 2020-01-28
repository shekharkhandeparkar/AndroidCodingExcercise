package co.shekhar.androidcodingexercise.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.shekhar.androidcodingexercise.R
import co.shekhar.androidcodingexercise.databinding.ItemFeedBinding
import co.shekhar.androidcodingexercise.model.Feed

class FeedListAdapter : RecyclerView.Adapter<FeedListAdapter.ViewHolder>() {
    var feedList: List<Feed>

    init {
        feedList = arrayListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFeedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_feed,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(feedList[position])
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    fun updateFeedList(feedList: List<Feed>) {
        this.feedList = feedList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = FeedViewModel()

        fun bind(feed: Feed) {
            viewModel.bind(feed)
            binding.viewModel = viewModel
        }
    }
}