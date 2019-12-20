package com.rapider.utils

import android.util.Log
import com.rapider.BuildConfig

interface Logger{
    fun log(tag:String?="",msg:String)
    fun debug(tag:String?="",msg:String)
    fun warn(tag:String?="",msg:String)
    fun error(tag:String?="",err:Throwable)
}

class CustomLogger:Logger{
    override fun log(tag: String?, msg: String) {
       Log.i(tag,msg)
    }

    override fun debug(tag: String?, msg: String) {
        if (BuildConfig.DEBUG){
            Log.d(tag,msg)
        }
    }

    override fun warn(tag: String?, msg: String) {
        Log.w(tag,msg)
    }

    override fun error(tag: String?, err: Throwable) {
        Log.e(tag,"",err)
    }

}