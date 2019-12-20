
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.extensions

import androidx.annotation.StringRes
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.rapider.AndroidApplication
import com.rapider.di.ApplicationComponent
import com.rapider.lib.browser.Components

/**
 * Get the requireComponents of this application.
 */
val androidx.fragment.app.Fragment.requireComponents: Components
    get() = requireContext().components
val androidx.fragment.app.Fragment.appComponent: ApplicationComponent
    get() = (requireContext().applicationContext as AndroidApplication).appComponent

fun PreferenceFragmentCompat.findPreferenceById(@StringRes id:Int):Preference?{
    return this.findPreference(this.context?.getString(id))
}