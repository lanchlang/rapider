/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.history

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.rapider.R
import com.rapider.base.BaseFragment
import com.rapider.extensions.appComponent

class HistoryFragment : BaseFragment() {
    lateinit var viewModel: HistoryViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
        viewModel= ViewModelProviders.of(this, factory).get(HistoryViewModel::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_guide
    }

}
