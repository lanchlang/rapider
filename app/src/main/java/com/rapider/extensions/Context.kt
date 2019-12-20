/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.util.TypedValue
import androidx.annotation.StringRes
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.rapider.AndroidApplication
import com.rapider.R
import com.rapider.lib.browser.Components
import mozilla.components.support.base.log.Log
import mozilla.components.support.base.log.Log.Priority.WARN

/**
 * Get the BrowserApplication object from a context.
 */
val Context.application: AndroidApplication
    get() = applicationContext as AndroidApplication

/**
 * Get the requireComponents of this application.
 */
val Context.components: Components
    get() = application.components

fun Context.getPreferenceKey(@StringRes resourceId: Int): String =
    resources.getString(resourceId)


/**
 *  Shares content via [ACTION_SEND] intent.
 *
 * @param text the data to be shared  [EXTRA_TEXT]
 * @param subject of the intent [EXTRA_TEXT]
 * @return true it is able to share false otherwise.
 */
fun Context.share(text: String, subject: String = ""): Boolean {
    return try {
        val intent = Intent(ACTION_SEND).apply {
            type = "text/plain"
            putExtra(EXTRA_SUBJECT, subject)
            putExtra(EXTRA_TEXT, text)
            flags = FLAG_ACTIVITY_NEW_TASK
        }

        val shareIntent = Intent.createChooser(intent, getString(R.string.menu_share_with)).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(shareIntent)
        true
    } catch (e: ActivityNotFoundException) {
        Log.log(WARN, message = "No activity to share to found", throwable = e, tag = "Reference-Browser")
        false
    }
}


fun Context.dp2px(dpValue:Int):Int{
    val scale: Float = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}
fun Context.sp2px(spValue:Float):Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, resources.displayMetrics)
}

fun Context.getSpBool(id:Int, defaultValue:Boolean=false):Boolean{
    return PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(id),defaultValue)
}
fun Context.getSpString(id:Int, defaultValue:String=""):String{
    return PreferenceManager.getDefaultSharedPreferences(this).getString(getString(id),defaultValue) ?: ""
}
fun Context.getSpFloat(id:Int, defaultValue:Float=0f):Float{
    return PreferenceManager.getDefaultSharedPreferences(this).getFloat(getString(id),defaultValue)
}
fun Context.getSpInt(id:Int, defaultValue:Int=0):Int{
    return PreferenceManager.getDefaultSharedPreferences(this).getInt(getString(id),defaultValue)
}
fun Context.getSpLong(id:Int, defaultValue:Long=0):Long{
    return PreferenceManager.getDefaultSharedPreferences(this).getLong(getString(id),defaultValue)
}
fun Context.getSpLong(id:Int, defaultValue:MutableSet<String> = HashSet()):MutableSet<String>{
    return PreferenceManager.getDefaultSharedPreferences(this).getStringSet(getString(id),defaultValue) ?: HashSet()
}
fun Context.setSpBool(id:Int, value:Boolean=false){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putBoolean(getString(id),value)
    }.apply()
}
fun Context.setSpString(id:Int, value:String=""){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putString(getString(id),value)
    }.apply()
}
fun Context.setSpFloat(id:Int, value:Float=0f){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putFloat(getString(id),value)
    }.apply()
}
fun Context.setSpInt(id:Int, value:Int=0){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putInt(getString(id),value)
    }.apply()
}
fun Context.setSpLong(id:Int, value:Long=0){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putLong(getString(id),value)
    }.apply()
}
fun Context.setSpLong(id:Int, value:MutableSet<String> = HashSet()){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putStringSet(getString(id),value)
    }.apply()
}

fun<T> Context.setSpData(pref_key:Int, data:T){
    val str= Gson().toJson(data)
    setSpString(pref_key,str)
}
inline fun<reified T> Context.getSpData(pref_key:Int):T?{
    val str=getSpString(pref_key)
    if (str.isBlank()) return null
    return Gson().fromJson<T>(str)
}
fun<T> Context.setSpList(pref_key:Int, data:List<T>){
    val str= Gson().toJson(data)
    setSpString(pref_key,str)
}
inline fun<reified T> Context.getSpList(pref_key:Int):List<T>{
    val str=getSpString(pref_key)
    if (str.isBlank()) return listOf()
    return Gson().fromJson<Array<T>>(str).toList()
}

