/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.lib.browser

import android.content.Context
import com.rapider.lib.browser.components.Core
import com.rapider.lib.browser.components.Analytics
import com.rapider.lib.browser.components.BackgroundServices
import com.rapider.lib.browser.components.Search
import com.rapider.lib.browser.components.Utilities
import com.rapider.lib.browser.components.UseCases

/**
 * Provides access to all components.
 */
class Components(private val context: Context) {
    val core by lazy { Core(context) }
    val search by lazy { Search(context) }
    val useCases by lazy {
        UseCases(
                context,
                core.sessionManager,
                core.store,
                core.engine.settings,
                search.searchEngineManager,
                core.client
        )
    }
    // Background services are initiated eagerly; they kick off periodic tasks and setup an accounts system.
    val backgroundServices by lazy { BackgroundServices(context, core.historyStorage) }

    val analytics by lazy { Analytics(context) }
    val utils by lazy {
        Utilities(context, core.sessionManager, useCases.sessionUseCases, useCases.searchUseCases)
    }
}