package co.shekhar.androidcodingexercise.ui

import androidx.lifecycle.MutableLiveData
import co.shekhar.androidcodingexercise.base.BaseViewModel
import co.shekhar.androidcodingexercise.model.Feed

class FeedViewModel : BaseViewModel() {
    private val feedTitle = MutableLiveData<String>()
    private val feedDescription = MutableLiveData<String>()
    private val feedImageHref = MutableLiveData<String>()

    fun bind(feed: Feed) {
        feedTitle.value = feed.title ?: "NA"
        feedDescription.value = feed.description ?: "NA"
        feedImageHref.value = feed.imageHref ?: "NA"
    }

    fun getFeedTitle(): MutableLiveData<String> {
        return feedTitle
    }

    fun getFeedDescription(): MutableLiveData<String> {
        return feedDescription
    }

    fun getFeedImageHref(): MutableLiveData<String> {
        return feedImageHref
    }
}