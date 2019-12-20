/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.rapider.extensions

import android.view.View

/**
 * Replaces the keys with the values with the map provided.
 */
fun View.hide(): Unit {
    this.visibility=View.GONE
}
fun View.show(){
    this.visibility=View.VISIBLE
}
