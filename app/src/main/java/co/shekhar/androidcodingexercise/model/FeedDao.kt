package co.shekhar.androidcodingexercise.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FeedDao {
    @get:Query("SELECT * FROM feed")
    val all: List<Feed>

    @Insert
    fun insertAll(vararg feeds: Feed)

    @Query("DELETE FROM feed")
    fun deleteAll()
}