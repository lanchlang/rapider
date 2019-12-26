/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.settings.privacy

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.rapider.R
import com.rapider.base.SimplePreferenceFragmentCompat
import com.rapider.extensions.*
import mozilla.components.support.base.feature.BackHandler

class PrivacySettingFragment : SimplePreferenceFragmentCompat(),BackHandler {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_adblock, rootKey)
    }
    override fun initView(view: View) {
        super.initView(view)
        view.findViewById<TextView>(R.id.title)?.apply {
            text = getString(R.string.privacy_settings)
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

                   true
               }
           }
    }
}
