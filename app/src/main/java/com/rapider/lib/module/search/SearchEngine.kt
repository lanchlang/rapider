package com.rapider.lib.module.search

import android.annotation.SuppressLint
import android.net.Uri
import mozilla.components.browser.search.SearchEngine

@SuppressLint("DefaultLocale")
fun SearchEngine.host():String{
    val searchUrl=  this.buildSearchUrl("host")
    return Uri.parse(searchUrl).host?.toLowerCase()?.removePrefix("www.") ?: this.name
}