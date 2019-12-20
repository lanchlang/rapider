package com.rapider.bloc.fragments.home

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.rapider.R
import com.rapider.base.BaseFragment
import com.rapider.extensions.appComponent
import mozilla.components.support.base.feature.BackHandler

class HomeFragment : BaseFragment(), BackHandler{
    lateinit var viewModel: HomeViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
        viewModel=ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun onBackPressed(): Boolean {
       return true
    }
}