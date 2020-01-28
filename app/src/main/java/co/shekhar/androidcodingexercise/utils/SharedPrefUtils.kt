package co.shekhar.androidcodingexercise.utils

import android.content.Context
import co.shekhar.androidcodingexercise.R

class SharedPrefUtils {
    companion object {

        val SHARED_PREF = "android_coding_exercise_app"

        val FEED_TITLE = "feed_title"

        fun storeValue(context: Context, key: String, value: String) {
            val sharedPreference = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getValue(context: Context, key: String): String? {
            val sharedPreference = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sharedPreference?.getString(key, context.getString(R.string.app_name))
        }
    }
}