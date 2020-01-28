package co.shekhar.androidcodingexercise.ui

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import co.shekhar.androidcodingexercise.R
import co.shekhar.androidcodingexercise.base.BaseViewModel
import co.shekhar.androidcodingexercise.model.Feed
import co.shekhar.androidcodingexercise.model.FeedDao
import co.shekhar.androidcodingexercise.model.FeedData
import co.shekhar.androidcodingexercise.network.FeedApi
import co.shekhar.androidcodingexercise.utils.SharedPrefUtils
import javax.inject.Inject

class FeedListViewModel(
    private val feedDao: FeedDao,
    private val context: Context
) : BaseViewModel() {
    @Inject
    lateinit var feedApi: FeedApi
    val feedListAdapter: FeedListAdapter = FeedListAdapter()

    val feedTitle: MutableLiveData<String> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        loadTitle()
        loadFeeds(false)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun refreshFeeds() {
        loadFeeds(true)
    }

    private fun loadTitle() {
        feedTitle.value = SharedPrefUtils.getValue(context, SharedPrefUtils.FEED_TITLE)
    }

    private fun loadFeeds(refresh: Boolean) {
        subscription = Observable.fromCallable { feedDao.all }
            .concatMap { dbFeedList ->
                if (dbFeedList.isNotEmpty() && !refresh) {
                    Observable.just(dbFeedList)
                } else {
                    feedApi.getFeeds().concatMap { apiFeedList ->
                        println("call done")
                        feedDao.deleteAll()
                        feedDao.insertAll(*apiFeedList.rows.toTypedArray())
                        SharedPrefUtils.storeValue(
                            context,
                            SharedPrefUtils.FEED_TITLE,
                            apiFeedList.title
                        )
                        Observable.just(apiFeedList.rows)
                    }
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onAPICalled() }
            .doOnTerminate { onDataRetrieved() }
            .subscribe(
                { result -> onDataRetrieveSuccess(result) },
                {
                    onDataRetrieveFailure()
                }
            )
    }

    private fun onAPICalled() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onDataRetrieved() {
        loadingVisibility.value = View.GONE
    }

    private fun onDataRetrieveSuccess(fList: List<Feed>) {
        feedListAdapter.updateFeedList(fList)
        loadTitle()
    }

    private fun onDataRetrieveFailure() {
        errorMessage.value = R.string.feed_error
    }
}