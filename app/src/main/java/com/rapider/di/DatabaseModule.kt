package com.rapider.di

import dagger.Module

@Module
class DatabaseModule {
    //返回appdatabase
//    @Provides
//    @Singleton
//    fun provideAppDatabase(applicationContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "db"
//        ).build()
//    }

//    @Provides
//    @Singleton
//    fun provideAdBlockFilterDao(appDatabase: AppDatabase): AdBlockFilterDao {
//        return appDatabase.adBlockFilterDao()
//    }
//    @Provides
//    @Singleton
//    fun provideSavedFileDao(appDatabase: AppDatabase): SavedFileDao {
//        return appDatabase.savedFileDao()
//    }
//    @Provides
//    @Singleton
//    fun provideMainPageSiteDao(appDatabase: AppDatabase): MainPageSiteDao {
//        return appDatabase.mainPageSiteDao()
//    }
//    @Provides
//    @Singleton
//    fun provideCommonSiteDao(appDatabase: AppDatabase): CommonSiteDao {
//        return appDatabase.commonSiteDao()
//    }
//    @Provides
//    @Singleton
//    fun provideSearchHistoryItemDao(appDatabase: AppDatabase): SearchHistoryItemDao {
//        return appDatabase.searchHistoryItemDao()
//    }
//    @Provides
//    @Singleton
//    fun provideSiteCategoryDao(appDatabase: AppDatabase): SiteCategoryDao {
//        return appDatabase.siteCategoryDao()
//    }
//    @Provides
//    @Singleton
//    fun provideImageModeMetaDao(appDatabase: AppDatabase): ImageModeMetaDao {
//        return appDatabase.imageModeMetaDao()
//    }
//    @Provides
//    @Singleton
//    fun provideBrowserDatabase(applicationContext: Context): BrowserDatabase {
//        return BrowserDatabase.get(applicationContext.applicationContext)
//    }
//    @Provides
//    @Singleton
//    fun provideHistoryDao(browserDatabase: BrowserDatabase): VisitHistoryDao {
//        return browserDatabase.historyDao()
//    }
//    @Provides
//    @Singleton
//    fun provideBookMarkDao(browserDatabase: BrowserDatabase): BookMarkDao {
//        return browserDatabase.bookMarkDao()
//    }
//    @Provides
//    @Singleton
//    fun provideBookMarkCategoryDao(browserDatabase: BrowserDatabase): BookMarkCategoryDao {
//        return browserDatabase.bookMarkCategoryDao()
//    }
//    @Provides
//    @Singleton
//    fun provideDownloadDao(browserDatabase: BrowserDatabase): DownloadDao {
//        return browserDatabase.downloadDao()
//    }
}
