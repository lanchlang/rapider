package com.rapider.di


import com.rapider.AndroidApplication
import com.rapider.bloc.activities.external_app.ExternalAppBrowserActivity
import com.rapider.bloc.activities.intent_receiver.IntentReceiverActivity
import com.rapider.bloc.activities.main.MainActivity
import com.rapider.bloc.activities.movies.MoviesActivity
import com.rapider.bloc.activities.news.NewsActivity
import com.rapider.bloc.activities.reader.ReaderActivity
import com.rapider.bloc.fragments.bookmark.BookmarkFragment
import com.rapider.bloc.fragments.browser.BrowserFragment
import com.rapider.bloc.fragments.download.DownloadFragment
import com.rapider.bloc.fragments.external_app_browser.ExternalAppBrowserFragment
import com.rapider.bloc.fragments.favor.FavorFragment
import com.rapider.bloc.fragments.guide.GuideFragment
import com.rapider.bloc.fragments.history.HistoryFragment
import com.rapider.bloc.fragments.home.HomeFragment
import com.rapider.bloc.fragments.settings.main.MainSettingFragment
import com.rapider.bloc.fragments.tabs.TabsTrayFragment
import com.rapider.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class, DatabaseModule::class,NetworkModule::class])
interface ApplicationComponent {
    //activity
    fun inject(application: AndroidApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(externalAppBrowserActivity: ExternalAppBrowserActivity)
    fun inject(intentReceiverActivity: IntentReceiverActivity)
    fun inject(newsActivity: NewsActivity)
    fun inject(readerActivity: ReaderActivity)
    fun inject(moviesActivity: MoviesActivity)
    //fragment for browser
    fun inject(homeFragment: HomeFragment)
    fun inject(browserFragment: BrowserFragment)
    fun inject(externalAppBrowserFragment: ExternalAppBrowserFragment)
    fun inject(tabsTrayFragment: TabsTrayFragment)
    fun inject(guideFragment: GuideFragment)
    fun inject(downloadFragment: DownloadFragment)
    fun inject(historyFragment: HistoryFragment)
    fun inject(bookmarkFragment: BookmarkFragment)
    fun inject(favorFragment: FavorFragment)
    //settings
    fun inject(mainSettingFragment: MainSettingFragment)

}
