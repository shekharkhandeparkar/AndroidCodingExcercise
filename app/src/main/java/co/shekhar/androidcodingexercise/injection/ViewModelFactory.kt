package co.shekhar.androidcodingexercise.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import co.shekhar.androidcodingexercise.model.database.AppDatabase
import co.shekhar.androidcodingexercise.ui.FeedListViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedListViewModel::class.java)) {
            val db =
                Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "feeds_db")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return FeedListViewModel(db.feedDao(), activity.applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}