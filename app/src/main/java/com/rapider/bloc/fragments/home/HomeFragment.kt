package com.rapider.bloc.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.rapider.R
import com.rapider.base.BaseFragment
import com.rapider.bloc.fragments.home.feature.BottomBarFeature
import com.rapider.bloc.fragments.home.feature.FavorsActionFeature
import com.rapider.bloc.fragments.home.feature.SearchBarFeature
import com.rapider.extensions.appComponent
import mozilla.components.support.base.feature.BackHandler
import mozilla.components.support.base.feature.ViewBoundFeatureWrapper

class HomeFragment : BaseFragment(), BackHandler{
    private val searchBarFeature = ViewBoundFeatureWrapper<SearchBarFeature>()
    private val favorsFeature = ViewBoundFeatureWrapper<FavorsActionFeature>()
    private val bottomBarFeature = ViewBoundFeatureWrapper<BottomBarFeature>()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchBarFeature.set(
                feature = SearchBarFeature(
                        fragment = this
                ),
                owner = this,
                view = view
        )
        favorsFeature.set(
                feature = FavorsActionFeature(
                        fragment = this
                ),
                owner = this,
                view = view
        )
        bottomBarFeature.set(
                feature = BottomBarFeature(
                        fragment = this
                ),
                owner = this,
                view = view
        )
    }
}