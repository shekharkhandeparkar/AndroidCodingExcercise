package co.shekhar.androidcodingexercise.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.shekhar.androidcodingexercise.model.Feed
import co.shekhar.androidcodingexercise.model.FeedDao

@Database(entities = [Feed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
}