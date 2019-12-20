package com.rapider.bloc.activities.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rapider.bloc.activities.browser.BrowserActivity
import com.rapider.extensions.appComponent

class MainActivity: BrowserActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //注入
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
}