package com.rapider.bloc.activities.news

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rapider.bloc.activities.browser.BrowserActivity
import com.rapider.extensions.appComponent

class NewsActivity: BrowserActivity() {
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //注入
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(NewsViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
}