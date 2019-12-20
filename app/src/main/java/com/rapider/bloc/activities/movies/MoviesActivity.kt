package com.rapider.bloc.activities.movies

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rapider.bloc.activities.browser.BrowserActivity
import com.rapider.extensions.appComponent

class MoviesActivity: BrowserActivity() {
    lateinit var viewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //注入
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
}