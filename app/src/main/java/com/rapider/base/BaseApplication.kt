package com.rapider.base

import android.app.Application
import com.rapider.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseApplication : Application(), CoroutineScope {
    private val job= SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job
    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
        initialize()
    }

    abstract fun injectMembers()
    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
//            if (LeakCanary.isInAnalyzerProcess(this)) {
//                // This process is dedicated to LeakCanary for heap analysis.
//                // You should not init your app in this process.
//                return
//            }
//            LeakCanary.install(this)
        }
    }
    open fun initialize(){}
    override fun onTerminate() {
        super.onTerminate()
        coroutineContext.cancelChildren()
    }
}