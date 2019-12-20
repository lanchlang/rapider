/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rapider.di.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rapider.bloc.activities.external_app.ExternalAppBrowserViewModel
import com.rapider.bloc.activities.intent_receiver.IntentReceiverViewModel
import com.rapider.bloc.activities.main.MainViewModel
import com.rapider.bloc.activities.movies.MoviesViewModel
import com.rapider.bloc.activities.news.NewsViewModel
import com.rapider.bloc.activities.reader.ReaderViewModel
import com.rapider.bloc.fragments.bookmark.BookmarkViewModel
import com.rapider.bloc.fragments.browser.BrowserViewModel
import com.rapider.bloc.fragments.download.DownloadViewModel
import com.rapider.bloc.fragments.favor.FavorViewModel
import com.rapider.bloc.fragments.guide.GuideViewModel
import com.rapider.bloc.fragments.history.HistoryViewModel
import com.rapider.bloc.fragments.home.HomeViewModel
import com.rapider.bloc.fragments.settings.main.MainSettingViewModel
import com.rapider.bloc.fragments.tabs.TabsTrayViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(MainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExternalAppBrowserViewModel::class)
    abstract fun bindExternalAppBrowserViewModel(ExternalAppBrowserViewModel: ExternalAppBrowserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IntentReceiverViewModel::class)
    abstract fun bindIntentReceiverViewModel(IntentReceiverViewModel: IntentReceiverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReaderViewModel::class)
    abstract fun bindReaderViewModel(ReaderViewModel: ReaderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(NewsViewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(MoviesViewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowserViewModel::class)
    abstract fun bindBrowserViewModel(BrowserViewModel: BrowserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GuideViewModel::class)
    abstract fun bindGuideViewModel(GuideViewModel: GuideViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(HomeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TabsTrayViewModel::class)
    abstract fun bindTabsTrayViewModel(TabsTrayViewModel: TabsTrayViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DownloadViewModel::class)
    abstract fun bindDownloadViewModel(DownloadViewModel: DownloadViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(HistoryViewModel: HistoryViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(BookmarkViewModel::class)
    abstract fun bindBookmarkViewModel(BookmarkViewModel: BookmarkViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavorViewModel::class)
    abstract fun bindFavorViewModel(FavorViewModel: FavorViewModel): ViewModel

    //settings
    @Binds
    @IntoMap
    @ViewModelKey(MainSettingViewModel::class)
    abstract fun bindMainSettingViewModel(MainSettingViewModel: MainSettingViewModel): ViewModel
}