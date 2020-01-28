package co.shekhar.androidcodingexercise.base

import androidx.lifecycle.ViewModel
import co.shekhar.androidcodingexercise.injection.component.DaggerViewModelInjector
import co.shekhar.androidcodingexercise.injection.component.ViewModelInjector
import co.shekhar.androidcodingexercise.injection.module.NetworkModule
import co.shekhar.androidcodingexercise.ui.FeedListViewModel
import co.shekhar.androidcodingexercise.ui.FeedViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is FeedListViewModel -> injector.inject(this)
            is FeedViewModel -> injector.inject(this)
        }
    }
}