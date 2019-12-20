package com.rapider

import com.rapider.base.BaseBrowserApplication
import com.rapider.di.ApplicationComponent
import com.rapider.di.ApplicationModule
import com.rapider.di.DaggerApplicationComponent
import com.rapider.di.DatabaseModule

open class AndroidApplication: BaseBrowserApplication() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .databaseModule(DatabaseModule())
                .build()
    }

    override fun injectMembers() {
        appComponent.inject(this)
    }
}