package com.rapider.bloc.fragments.home.feature

import android.view.View
import com.rapider.R
import com.rapider.bloc.fragments.home.HomeFragment
import mozilla.components.support.base.feature.LifecycleAwareFeature

class SearchBarFeature(var fragment: HomeFragment) : LifecycleAwareFeature {
    var searchBar:View?=null
    var cameraIcon:View?=null
    var voiceIcon:View?=null
    init {
        searchBar= fragment.view?.findViewById<View>(R.id.search_bar).apply{
             //to search fragment
        }
        cameraIcon=fragment.view?.findViewById<View>(R.id.camera).apply{
             //to scan fragment
        }
        voiceIcon=fragment.view?.findViewById<View>(R.id.voice).apply{
             //popup voice search menu
        }
    }
    override fun start() {

    }

    override fun stop() {

    }

}