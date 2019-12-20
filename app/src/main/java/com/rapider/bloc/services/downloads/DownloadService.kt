/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.services.downloads

import mozilla.components.feature.downloads.AbstractFetchDownloadService
import com.rapider.extensions.components

class DownloadService : AbstractFetchDownloadService() {
    override val httpClient by lazy { components.core.client }
}
