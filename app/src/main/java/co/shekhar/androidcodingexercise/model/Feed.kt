package co.shekhar.androidcodingexercise.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Feed Class
 * @property id - unique identifier of feed
 * @property title - title of the feed
 * @property description - description of the feed
 * @property imageHref - image url/link of the feed
 */
@Entity
data class Feed(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String? = null,
    var description: String? = null,
    var imageHref: String? = null
) {
    constructor() : this(0, "", "", "")
}