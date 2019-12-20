/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.activities.external_app

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rapider.bloc.activities.browser.BrowserActivity
import mozilla.components.concept.engine.manifest.WebAppManifest
import com.rapider.bloc.fragments.external_app_browser.ExternalAppBrowserFragment
import com.rapider.extensions.appComponent
import mozilla.components.feature.pwa.ext.getWebAppManifest

/**
 * Activity that holds the BrowserFragment that is launched within an external app,
 * such as custom tabs and progressive web apps.
 */
class ExternalAppBrowserActivity : BrowserActivity() {
    lateinit var viewModel: ExternalAppBrowserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //注入
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(ExternalAppBrowserViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
    override fun createBrowserFragment(sessionId: String?): Fragment =
        if (sessionId != null) {
            val manifest = intent.getWebAppManifest()
            val scope = when (manifest?.display) {
                WebAppManifest.DisplayMode.FULLSCREEN,
                WebAppManifest.DisplayMode.STANDALONE -> Uri.parse(manifest.scope ?: manifest.startUrl)

                WebAppManifest.DisplayMode.MINIMAL_UI,
                WebAppManifest.DisplayMode.BROWSER -> null
                else -> null
            }

            ExternalAppBrowserFragment.create(
                sessionId,
                manifest,
                listOfNotNull(scope)
            )
        } else {
            // Fall back to browser fragment
            super.createBrowserFragment(sessionId)
        }
}
