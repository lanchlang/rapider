package com.rapider.bloc.fragments.favor

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.rapider.R
import com.rapider.base.BaseFragment
import com.rapider.extensions.appComponent
import mozilla.components.support.base.feature.BackHandler

class FavorFragment : BaseFragment(), BackHandler{
    lateinit var viewModel: FavorViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
        viewModel=ViewModelProviders.of(this, factory).get(FavorViewModel::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun onBackPressed(): Boolean {
       return true
    }
}