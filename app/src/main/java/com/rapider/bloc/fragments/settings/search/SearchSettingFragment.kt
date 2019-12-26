/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.bloc.fragments.settings.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.preference.PreferenceCategory
import com.rapider.R
import com.rapider.base.SimplePreferenceFragmentCompat
import com.rapider.extensions.*
import com.rapider.lib.module.search.host
import com.rapider.views.preferences.SingleSelectItemPreference
import com.rapider.views.preferences.SingleSelectItemPreferenceHelper
import mozilla.components.browser.search.SearchEngine
import mozilla.components.browser.search.SearchEngineManager
import mozilla.components.support.base.feature.BackHandler

class SearchSettingFragment : SimplePreferenceFragmentCompat(),BackHandler {
    private lateinit var singleSelectItemPreferenceHelper: SingleSelectItemPreferenceHelper
    private var defaultSearchEnginePreferenceCategory: PreferenceCategory? = null
    lateinit var searchEngineManager: SearchEngineManager
    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchEngineManager=requireComponents.search.searchEngineManager
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_search, rootKey)
    }
    override fun initView(view: View) {
        super.initView(view)
        view.findViewById<TextView>(R.id.title)?.apply {
            text = getString(R.string.search_engine)
        }
    }
    override fun setUpToolbar(toolbar: LinearLayout?) {
        toolbar?.findViewById<View>(R.id.back_icon)?.apply {
            setOnClickListener{
                onBackPressed()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        setupPreferences()
    }

    private fun setupPreferences() {
        listView.itemAnimator= null
        defaultSearchEnginePreferenceCategory = findPreferenceById(R.string.pref_key_search_engine_category) as PreferenceCategory
        singleSelectItemPreferenceHelper = SingleSelectItemPreferenceHelper(requireActivity(),
                R.string.pref_key_search_engine,
                "search_engine_Bing_key").apply {
            listener = object : SingleSelectItemPreferenceHelper.SingleSelectListener {
                override fun onSelect(preference: SingleSelectItemPreference?) {
                    preference?.apply {
                        saveSearchEngine(this.title as String)
                    }
                }
            }
        }
        addSearchEngineToCategory(defaultSearchEnginePreferenceCategory,searchEngineManager.getSearchEngines(requireContext()))
    }
    //保存搜索引擎
    private fun saveSearchEngine(name: String) {
        requireActivity().setSpString(R.string.pref_key_search_engine_name, name)
    }

    private fun addSearchEngineToCategory(preferenceCategory: PreferenceCategory?,searchEngineList:List<SearchEngine>){
        searchEngineList.forEach {
            //添加的搜索引擎
            val preference=SingleSelectItemPreference(requireContext()).apply {
                key="search_engine_"+it.name+"_key"
                title=it.name
                summary=it.host()
            }
            singleSelectItemPreferenceHelper.register(preference)
            preferenceCategory?.addPreference(preference)
        }
    }
}
