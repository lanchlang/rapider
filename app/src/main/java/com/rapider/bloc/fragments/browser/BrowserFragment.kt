/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.browser

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.rapider.MainNavDirections
import kotlinx.android.synthetic.main.fragment_browser.*
import kotlinx.android.synthetic.main.fragment_browser.view.*
import mozilla.components.feature.session.ThumbnailsFeature
import mozilla.components.feature.tabs.toolbar.TabsToolbarFeature
import mozilla.components.support.base.feature.BackHandler
import mozilla.components.support.base.feature.ViewBoundFeatureWrapper
import com.rapider.bloc.fragments.browser.integrations.ReaderViewIntegration
import com.rapider.lib.browser.UserInteractionHandler
import com.rapider.extensions.requireComponents
import com.rapider.extensions.appComponent
import mozilla.components.feature.awesomebar.AwesomeBarFeature

/**
 * Fragment used for browsing the web within the main app.
 */
class BrowserFragment : BaseBrowserFragment(), BackHandler, UserInteractionHandler {
    lateinit var viewModel: BrowserViewModel
    private val thumbnailsFeature = ViewBoundFeatureWrapper<ThumbnailsFeature>()
    private val readerViewFeature = ViewBoundFeatureWrapper<ReaderViewIntegration>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(BrowserViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AwesomeBarFeature(awesomeBar, toolbar, engineView)
            .addSearchProvider(
                requireContext(),
                requireComponents.search.searchEngineManager,
                requireComponents.useCases.searchUseCases.defaultSearch,
                requireComponents.core.client)
            .addSessionProvider(
                requireComponents.core.sessionManager,
                requireComponents.useCases.tabsUseCases.selectTab)
            .addHistoryProvider(
                requireComponents.core.historyStorage,
                requireComponents.useCases.sessionUseCases.loadUrl)
            .addClipboardProvider(requireContext(), requireComponents.useCases.sessionUseCases.loadUrl)

        TabsToolbarFeature(
            toolbar = toolbar,
            sessionId = sessionId,
            sessionManager = requireComponents.core.sessionManager,
            showTabs = ::showTabs)

        thumbnailsFeature.set(
                feature = ThumbnailsFeature(requireContext(),
                        engineView,
                        requireComponents.core.sessionManager),
                owner = this,
                view = view
        )

        readerViewFeature.set(
            feature = ReaderViewIntegration(
                    requireContext(),
                    requireComponents.core.engine,
                    requireComponents.core.sessionManager,
                    view.toolbar,
                    view.readerViewBar,
                    view.readerViewAppearanceButton
            ),
            owner = this,
            view = view
        )
    }

    private fun showTabs() {
        val action=MainNavDirections.actionGlobalTabsTrayFragment()
        findNavController().navigate(action)
    }

    override fun onBackPressed(): Boolean =
        readerViewFeature.onBackPressed() || super.onBackPressed()

    companion object {
        fun create(sessionId: String? = null) = BrowserFragment().apply {
            arguments = Bundle().apply {
                putSessionId(sessionId)
            }
        }
    }
}
