package co.shekhar.androidcodingexercise.injection.component

import dagger.Component
import co.shekhar.androidcodingexercise.injection.module.NetworkModule
import co.shekhar.androidcodingexercise.ui.FeedListViewModel
import co.shekhar.androidcodingexercise.ui.FeedViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified FeedListViewModel.
     * @param feedListViewModel FeedListViewModel in which to inject the dependencies
     */
    fun inject(feedListViewModel: FeedListViewModel)

    /**
     * Injects required dependencies into the specified FeedViewModel.
     * @param feedViewModel FeedViewModel in which to inject the dependencies
     */
    fun inject(feedViewModel: FeedViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}