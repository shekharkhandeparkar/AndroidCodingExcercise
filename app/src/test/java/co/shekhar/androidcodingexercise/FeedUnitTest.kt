package co.shekhar.androidcodingexercise

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.shekhar.androidcodingexercise.model.Feed
import co.shekhar.androidcodingexercise.model.FeedDao
import co.shekhar.androidcodingexercise.model.database.AppDatabase
import co.shekhar.androidcodingexercise.network.FeedApi
import co.shekhar.androidcodingexercise.ui.FeedListViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeedUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var vm: FeedListViewModel
    @Mock
    var context: Context? = null
    @Mock
    var appDatabase: AppDatabase = mock()
    var feeds = ArrayList<Feed>()
    @Mock
    lateinit var feedInfoDao: FeedDao

    @Mock
    var feedApi: FeedApi = mock()

    @Spy
    var feedApi2: FeedApi = spy()

    companion object {
        private val testScheduler: TestScheduler = TestScheduler()

        init {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> testScheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> testScheduler }
            RxJavaPlugins.setInitComputationSchedulerHandler { _ -> testScheduler }
            RxJavaPlugins.setInitIoSchedulerHandler { _ -> testScheduler }
        }
    }

    @Before
    fun setup() {
        context = mock(Context::class.java)
        feedInfoDao = mock(FeedDao::class.java)
        vm = FeedListViewModel(feedInfoDao, context!!)
        vm.feedApi = feedApi
    }

    @Test
    fun test1() {
        initDummy()
        Mockito.`when`(appDatabase.feedDao())
            .thenReturn(feedInfoDao)
        Mockito.`when`(appDatabase.feedDao().all)
            .thenReturn(feeds)
        Assert.assertEquals(2, feeds.size)
    }

    @Test
    fun test2() {
        initDummy()
        Mockito.`when`(appDatabase.feedDao())
            .thenReturn(feedInfoDao)
        Mockito.`when`(appDatabase.feedDao().all)
            .thenReturn(listOf(Feed()))
        vm.refreshFeeds()
        Assert.assertEquals(0, feeds.size)
    }

    @Test
    fun test3() {
        initDummy()
        vm.refreshFeeds()
        Assert.assertNotNull(vm.feedListAdapter.feedList)
        Assert.assertEquals(2, feeds.size)
    }

    @Test
    fun test4() {
        initDummy()
        Mockito.`when`(appDatabase.feedDao())
            .thenReturn(feedInfoDao)
        Mockito.`when`(appDatabase.feedDao().all)
            .thenReturn(feeds)

        Assert.assertNotNull(feeds)
        Assert.assertEquals("Feed Title", feeds[0].title)
    }

    private fun initDummy() {
        val feed = Feed()
        feed.title = "Feed Title"
        feed.description = "Feed Description"
        feeds.add(feed)
        feeds.add(Feed())
    }

}