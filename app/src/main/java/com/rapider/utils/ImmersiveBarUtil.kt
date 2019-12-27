package com.rapider.utils

import android.app.Activity
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.rapider.R
import com.rapider.extensions.getSpBool
import razerdp.basepopup.BasePopupWindow

fun showStatusBar(activity:Activity,show:Boolean=true){
    if (show){
        ImmersionBar.with(activity)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .navigationBarDarkIcon(true)
                .navigationBarColor(R.color.photonWhite)
                .hideBar(BarHide.FLAG_SHOW_BAR)
                .init()
    }else{
        ImmersionBar.with(activity)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .navigationBarDarkIcon(true)
                .navigationBarColor(R.color.photonWhite)
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                .init()
    }
}

//当有弹窗弹出时，会显示StatusBar
fun fixStatusBarStateForPopupShow(activity: Activity,basePopupWindow: BasePopupWindow){
    basePopupWindow.onDismissListener = object:BasePopupWindow.OnDismissListener(){
        override fun onDismiss() {
            val showStatusBar=activity.getSpBool(R.string.pref_key_show_status_bar,true)
            showStatusBar(activity,showStatusBar)
        }
    }
}