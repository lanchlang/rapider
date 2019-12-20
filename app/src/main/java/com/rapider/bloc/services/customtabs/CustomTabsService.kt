/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.rapider.bloc.services.customtabs

import mozilla.components.concept.engine.Engine
import com.rapider.extensions.components
import mozilla.components.feature.customtabs.AbstractCustomTabsService

class CustomTabsService : AbstractCustomTabsService() {
    override val engine: Engine by lazy { components.core.engine }
}
