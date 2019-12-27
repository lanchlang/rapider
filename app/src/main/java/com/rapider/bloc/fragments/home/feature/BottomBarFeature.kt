package com.rapider.bloc.fragments.home.feature

import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.rapider.R
import com.rapider.bloc.fragments.home.HomeFragment
import com.rapider.bloc.fragments.home.helper.MenuPopupHelper
import mozilla.components.support.base.feature.LifecycleAwareFeature

class BottomBarFeature(var fragment: HomeFragment) : LifecycleAwareFeature {
    var pageIcon:View?=null
    var tabCounterIcon:View?=null
    var tabCounterText: TextView?=null
    var robotIcon:View?=null
    var menuIcon:View?=null
    var menuPopupHelper:MenuPopupHelper?=null
    init {
        menuPopupHelper= MenuPopupHelper(fragment,fragment.requireContext())
        pageIcon=fragment.view?.findViewById<View>(R.id.page)?.apply {
            //TODO navigate to ReadActivity
        }
        tabCounterIcon=fragment.view?.findViewById<View>(R.id.tab_counter)?.apply {

        }
        tabCounterText=fragment.view?.findViewById<TextView>(R.id.tab_counter_text)?.apply {

        }
        robotIcon=fragment.view?.findViewById<View>(R.id.robot)?.apply {

        }
        menuIcon=fragment.view?.findViewById<View>(R.id.menu)?.apply {
            setOnClickListener {
                menuPopupHelper?.show()
            }
        }
    }
    override fun start() {

    }

    override fun stop() {

    }

}