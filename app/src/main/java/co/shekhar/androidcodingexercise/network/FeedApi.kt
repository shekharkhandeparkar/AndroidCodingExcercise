package co.shekhar.androidcodingexercise.network

import io.reactivex.Observable
import co.shekhar.androidcodingexercise.model.FeedData
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface FeedApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFeeds(): Observable<FeedData>
}