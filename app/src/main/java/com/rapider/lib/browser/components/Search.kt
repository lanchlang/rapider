/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.lib.browser.components

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mozilla.components.browser.search.SearchEngineManager

/**
 * Component group for all search engine integration related functionality.
 */
class Search(private val context: Context) {

    /**
     * This component provides access to a centralized registry of search engines.
     */
    val searchEngineManager by lazy {
        SearchEngineManager().apply {
            GlobalScope.launch {
                loadAsync(context).await()
            }
        }
    }
}
