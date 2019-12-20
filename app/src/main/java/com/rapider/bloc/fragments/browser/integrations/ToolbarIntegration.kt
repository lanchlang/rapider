/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.browser.integrations

import android.content.Context
import androidx.navigation.findNavController
import com.rapider.MainNavDirections
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mozilla.components.browser.domains.autocomplete.ShippedDomainsProvider
import mozilla.components.browser.menu.BrowserMenuBuilder
import mozilla.components.browser.menu.BrowserMenuItem
import mozilla.components.browser.menu.item.BrowserMenuItemToolbar
import mozilla.components.browser.menu.item.BrowserMenuSwitch
import mozilla.components.browser.menu.item.SimpleBrowserMenuItem
import mozilla.components.browser.session.SessionManager
import mozilla.components.browser.toolbar.BrowserToolbar
import mozilla.components.browser.toolbar.display.DisplayToolbar
import mozilla.components.concept.storage.HistoryStorage
import mozilla.components.feature.session.SessionUseCases
import mozilla.components.feature.tabs.TabsUseCases
import mozilla.components.feature.toolbar.ToolbarAutocompleteFeature
import mozilla.components.feature.toolbar.ToolbarFeature
import mozilla.components.support.base.feature.BackHandler
import mozilla.components.support.base.feature.LifecycleAwareFeature
import com.rapider.R
import com.rapider.extensions.components
import com.rapider.extensions.share
import mozilla.components.feature.pwa.WebAppUseCases

class ToolbarIntegration(
        context: Context,
        var toolbar: BrowserToolbar,
        historyStorage: HistoryStorage,
        sessionManager: SessionManager,
        sessionUseCases: SessionUseCases,
        tabsUseCases: TabsUseCases,
        webAppUseCases: WebAppUseCases,
        sessionId: String? = null
) : LifecycleAwareFeature, BackHandler {
    private val shippedDomainsProvider = ShippedDomainsProvider().also {
        it.initialize(context)
    }

    private val menuToolbar by lazy {
        val forward = BrowserMenuItemToolbar.Button(
            R.drawable.mozac_ic_forward,
            iconTintColorResource = R.color.icons,
            contentDescription = "Forward",
            isEnabled = { sessionManager.selectedSession?.canGoForward == true }) {
            sessionUseCases.goForward.invoke()
        }

        val refresh = BrowserMenuItemToolbar.Button(
            R.drawable.mozac_ic_refresh,
            iconTintColorResource = R.color.icons,
            contentDescription = "Refresh") {
            sessionUseCases.reload.invoke()
        }

        val stop = BrowserMenuItemToolbar.Button(
            R.drawable.mozac_ic_stop,
            iconTintColorResource = R.color.icons,
            contentDescription = "Stop") {
            sessionUseCases.stopLoading.invoke()
        }

        BrowserMenuItemToolbar(listOf(forward, refresh, stop))
    }

    private val menuItems: List<BrowserMenuItem> by lazy {
        listOf(
            menuToolbar,
            SimpleBrowserMenuItem("Share") {
                val url = sessionManager.selectedSession?.url ?: ""
                context.share(url)
            }.apply {
                visible = { sessionManager.selectedSession != null }
            },

            BrowserMenuSwitch("Request desktop site", {
                sessionManager.selectedSessionOrThrow.desktopMode
            }) { checked ->
                sessionUseCases.requestDesktopSite.invoke(checked)
            }.apply {
                visible = { sessionManager.selectedSession != null }
            },

            SimpleBrowserMenuItem("Add to homescreen") {
                MainScope().launch { webAppUseCases.addToHomescreen() }
            }.apply {
                visible = { webAppUseCases.isPinningSupported() }
            },

            SimpleBrowserMenuItem("Find in Page") {
                FindInPageIntegration.launch?.invoke()
            }.apply {
                visible = { sessionManager.selectedSession != null }
            },

            SimpleBrowserMenuItem("Report issue") {
                tabsUseCases.addTab.invoke(
                    "https://github.com/mozilla-mobile/reference-browser/issues/new")
            },

            SimpleBrowserMenuItem("Settings") {
                openSettings()
            }
        )
    }

    private val menuBuilder = BrowserMenuBuilder(menuItems)

    init {
        toolbar.display.indicators = listOf(
            DisplayToolbar.Indicators.SECURITY,
            DisplayToolbar.Indicators.TRACKING_PROTECTION
        )
        toolbar.display.displayIndicatorSeparator = true
        toolbar.display.menuBuilder = menuBuilder

        toolbar.display.hint = context.getString(R.string.toolbar_hint)
        toolbar.edit.hint = context.getString(R.string.toolbar_hint)

        ToolbarAutocompleteFeature(toolbar).apply {
            addHistoryStorageProvider(historyStorage)
            addDomainProvider(shippedDomainsProvider)
        }

        toolbar.display.setUrlBackground(context.resources.getDrawable(R.drawable.url_background, context.theme))
    }

    private val toolbarFeature: ToolbarFeature = ToolbarFeature(
        toolbar,
        context.components.core.store,
        context.components.useCases.sessionUseCases.loadUrl,
        { searchTerms -> context.components.useCases.searchUseCases.defaultSearch.invoke(searchTerms) },
        sessionId
    )

    override fun start() {
        toolbarFeature.start()
    }

    override fun stop() {
        toolbarFeature.stop()
    }

    override fun onBackPressed(): Boolean {
        return toolbarFeature.onBackPressed()
    }

    private fun openSettings() {
        val action=MainNavDirections.actionGlobalMainSettingFragment()
       toolbar.findNavController().navigate(action)
    }
}
