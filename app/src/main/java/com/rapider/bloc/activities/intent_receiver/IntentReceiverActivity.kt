/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.activities.intent_receiver

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rapider.bloc.activities.browser.BrowserActivity
import com.rapider.bloc.activities.external_app.ExternalAppBrowserActivity
import com.rapider.extensions.appComponent
import com.rapider.extensions.components
import com.rapider.utils.Logger
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntentReceiverActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var logger: Logger
    lateinit var viewModel:IntentReceiverViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //注入
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(IntentReceiverViewModel::class.java)

        super.onCreate(savedInstanceState)
        val intent = intent?.let { Intent(it) } ?: Intent()
        val utils = components.utils

        MainScope().launch {
            utils.intentProcessors.any {
                it.process(intent)
            }

            val className = if (utils.externalIntentProcessors.any {
                        it.matches(intent)
                    }) {
                ExternalAppBrowserActivity::class
            } else {
                BrowserActivity::class
            }
            intent.setClassName(applicationContext, className.java.name)

            startActivity(intent)
            finish()
        }
    }
}
