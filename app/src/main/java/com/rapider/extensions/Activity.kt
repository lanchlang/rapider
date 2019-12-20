package com.rapider.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.rapider.AndroidApplication
import com.rapider.di.ApplicationComponent


val AppCompatActivity.appComponent: ApplicationComponent
    get() = (applicationContext as AndroidApplication).appComponent

fun Activity.getSpBool(id:Int, defaultValue:Boolean=false):Boolean{
        return PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(id),defaultValue)
}
fun Activity.getSpString(id:Int, defaultValue:String=""):String{
    return PreferenceManager.getDefaultSharedPreferences(this).getString(getString(id),defaultValue) ?: ""
}
fun Activity.getSpFloat(id:Int, defaultValue:Float=0f):Float{
    return PreferenceManager.getDefaultSharedPreferences(this).getFloat(getString(id),defaultValue)
}
fun Activity.getSpInt(id:Int, defaultValue:Int=0):Int{
    return PreferenceManager.getDefaultSharedPreferences(this).getInt(getString(id),defaultValue)
}
fun Activity.getSpLong(id:Int, defaultValue:Long=0):Long{
    return PreferenceManager.getDefaultSharedPreferences(this).getLong(getString(id),defaultValue)
}
fun Activity.getSpLong(id:Int, defaultValue:MutableSet<String> = HashSet()):MutableSet<String>{
    return PreferenceManager.getDefaultSharedPreferences(this).getStringSet(getString(id),defaultValue) ?: HashSet()
}
fun Activity.setSpBool(id:Int, value:Boolean=false){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putBoolean(getString(id),value)
    }.apply()
}
fun Activity.setSpString(id:Int, value:String=""){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putString(getString(id),value)
    }.apply()
}
fun Activity.setSpFloat(id:Int, value:Float=0f){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putFloat(getString(id),value)
    }.apply()
}
fun Activity.setSpInt(id:Int, value:Int=0){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putInt(getString(id),value)
    }.apply()
}
fun Activity.setSpLong(id:Int, value:Long=0){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putLong(getString(id),value)
    }.apply()
}
fun Activity.setSpLong(id:Int, value:MutableSet<String> = HashSet()){
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        this.putStringSet(getString(id),value)
    }.apply()
}

fun Activity.openNotificationSetting(){
    val intent = Intent()
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, this.packageName)
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("app_package", packageName)
            intent.putExtra("app_uid", applicationInfo.uid)
        }
        else -> {
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:$packageName")
        }
    }
    startActivity(intent)
}

fun Activity.openApplicationSetting(){
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}