/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.settings.general

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.rapider.MainNavDirections
import com.rapider.R
import com.rapider.base.SimplePreferenceFragmentCompat
import com.rapider.extensions.dp2px
import com.rapider.extensions.findPreferenceById
import com.rapider.extensions.getSpString
import com.rapider.extensions.setSpString
import com.rapider.views.RoundRectOutlineProvider
import com.rapider.views.SelectableTextView
import mozilla.components.support.base.feature.BackHandler
import razerdp.basepopup.BasePopupWindow
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig

class GeneralSettingFragment : SimplePreferenceFragmentCompat(), BackHandler {
    private var uaPopupHelper:UaPopupHelper?=null
    private var preLoadPopupHelper:PreloadPopupHelper?=null
    private var autoRecoverPopupHelper:AutoRecoverPopupHelper?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        uaPopupHelper= UaPopupHelper(this,this.requireContext())
        preLoadPopupHelper= PreloadPopupHelper(this,this.requireContext())
        autoRecoverPopupHelper= AutoRecoverPopupHelper(this,this.requireContext())
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_general, rootKey)
    }

    override fun initView(view: View) {
        super.initView(view)
        view.findViewById<TextView>(R.id.title)?.apply {
            text = getString(R.string.general)
        }

    }

    override fun setUpToolbar(toolbar: LinearLayout?) {
        toolbar?.findViewById<View>(R.id.back_icon)?.apply {
            setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setupPreferences()
    }

    private fun setupPreferences() {
        findPreferenceById(R.string.pref_key_clear_cache_display)?.apply {
            this.setOnPreferenceClickListener {
                val direction = MainNavDirections.actionGlobalCacheSettingFragment()
                findNavController().navigate(direction)
                true
            }
        }
        findPreferenceById(R.string.pref_key_search_engine_display)?.apply {
            this.summary = requireContext().getSpString(R.string.pref_key_search_engine_name)
            this.setOnPreferenceClickListener {
                val direction = MainNavDirections.actionGlobalSearchSettingFragment()
                findNavController().navigate(direction)
                true
            }
        }
        findPreferenceById(R.string.pref_key_ua_settings_display)?.apply {
            this.summary = requireContext().getSpString(R.string.pref_key_user_agent, getString(R.string.ua_android))
            this.setOnPreferenceClickListener {
                uaPopupHelper?.showUaPopup()
                true
            }
        }
        findPreferenceById(R.string.pref_key_pre_load_display)?.apply {
            this.summary=requireContext().getSpString(R.string.pref_key_pre_load,getString(R.string.always_open))
            this.setOnPreferenceClickListener {
                preLoadPopupHelper?.showPreLoadPopup()
                true
            }
        }
        findPreferenceById(R.string.pref_key_auto_recover)?.apply {
            val storeValue=requireContext().getSpString(R.string.pref_key_auto_recover,getString(R.string.auto_recovery))
            if (storeValue==getString(R.string.auto_recovery) || storeValue==getString(R.string.ask_before_auto_recovery)){
                this.summary=getString(R.string.open)
            }else{
                this.summary=getString(R.string.close)
            }
            this.setOnPreferenceClickListener {
                autoRecoverPopupHelper?.showAutoRecoverPopup()
                true
            }
        }
    }



}
