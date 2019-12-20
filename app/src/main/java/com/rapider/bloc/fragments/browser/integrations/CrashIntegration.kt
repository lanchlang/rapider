/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.browser.integrations

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rapider.base.BaseBrowserApplication.Companion.NON_FATAL_CRASH_BROADCAST
import com.rapider.extensions.isCrashReportActive

class CrashIntegration(
    private val context: Context
) : LifecycleObserver {

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (isCrashReportActive) {
            context.registerReceiver(receiver, IntentFilter(NON_FATAL_CRASH_BROADCAST))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        if (isCrashReportActive) {
            context.unregisterReceiver(receiver)
        }
    }
}
