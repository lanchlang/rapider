package com.rapider.bloc.activities.reader

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rapider.bloc.activities.browser.BrowserActivity
import com.rapider.extensions.appComponent

class ReaderActivity: BrowserActivity() {
    lateinit var viewModel: ReaderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //注入
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(ReaderViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
}