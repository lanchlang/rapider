/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.settings.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.rapider.MainNavDirections
import com.rapider.R
import com.rapider.base.BasePreferenceFragmentCompat
import com.rapider.extensions.*
import mozilla.components.support.base.feature.BackHandler

class MainSettingFragment : BasePreferenceFragmentCompat(),BackHandler {
    lateinit var viewModel: MainSettingViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
        viewModel= ViewModelProviders.of(this, factory).get(MainSettingViewModel::class.java)
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_main, rootKey)
    }
    override fun initView(view: View) {
        super.initView(view)
        view.findViewById<TextView>(R.id.title)?.apply {
            text = getString(R.string.setting)
        }

    }
    override fun setUpToolbar(toolbar: LinearLayout?) {
        toolbar?.findViewById<View>(R.id.back_icon)?.apply {
            setOnClickListener{
                onBackPressed()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        setupPreferences()
    }

    private fun setupPreferences() {
           findPreferenceById(R.string.pref_key_general_display)?.apply {
               this.setOnPreferenceClickListener {
                   val direction=MainNavDirections.actionGlobalGeneralSettingFragment()
                   findNavController().navigate(direction)
                   true
               }
           }
        findPreferenceById(R.string.pref_key_toolbar_display)?.apply {
            this.setOnPreferenceClickListener {
                val direction=MainNavDirections.actionGlobalToolbarSettingFragment()
                findNavController().navigate(direction)
                true
            }
        }
        findPreferenceById(R.string.pref_key_ad_block_display)?.apply {
            this.setOnPreferenceClickListener {
                val direction=MainNavDirections.actionGlobalAdblockSettingFragment()
                findNavController().navigate(direction)
                true
            }
        }
        findPreferenceById(R.string.pref_key_privacy_settings_display)?.apply {
            this.setOnPreferenceClickListener {
                val direction=MainNavDirections.actionGlobalPrivacySettingFragment()
                findNavController().navigate(direction)
                true
            }
        }
    }
}
