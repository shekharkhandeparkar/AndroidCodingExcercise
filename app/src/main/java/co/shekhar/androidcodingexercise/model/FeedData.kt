package co.shekhar.androidcodingexercise.model

/**
 * FeedData Class
 * @property title - title of the feed data
 * @property rows - feed list
 */
data class FeedData(
        val title: String,
        val rows: List<Feed>
)