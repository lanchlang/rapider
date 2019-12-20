/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.tabs

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.rapider.R
import com.rapider.base.BaseFragment
import com.rapider.extensions.appComponent
import mozilla.components.feature.tabs.tabstray.TabsFeature
import mozilla.components.support.base.feature.BackHandler

/**
 * A fragment for displaying the tabs tray.
 */
class TabsTrayFragment : BaseFragment(), BackHandler {
    private var tabsFeature: TabsFeature? = null
    lateinit var viewModel:TabsTrayViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
        viewModel= ViewModelProviders.of(this, factory).get(TabsTrayViewModel::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_tabstray
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        tabsFeature = TabsFeature(
//            tabsTray,
//            requireComponents.core.sessionManager,
//            requireComponents.useCases.tabsUseCases,
//            ::closeTabsTray)

        //tabsPanel.initialize(tabsFeature) { closeTabsTray() }
    }

    override fun onStart() {
        super.onStart()

        tabsFeature?.start()
    }

    override fun onStop() {
        super.onStop()

        tabsFeature?.stop()
    }

    override fun onBackPressed(): Boolean {
        findNavController().popBackStack()
        return true
    }


    //选中tab或者关闭tab时，跳转到browserfragment
    private fun closeTabsTray() {
        val action=TabsTrayFragmentDirections.actionGlobalBrowserFragment()
        findNavController().navigate(action)
    }
}
