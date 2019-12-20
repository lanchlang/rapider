package com.rapider.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel(), CoroutineScope {
    private val job= SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}