package com.rapider.bloc.fragments.settings.general

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import androidx.preference.PreferenceFragmentCompat
import com.rapider.R
import com.rapider.extensions.dp2px
import com.rapider.extensions.findPreferenceById
import com.rapider.extensions.getSpString
import com.rapider.extensions.setSpString
import com.rapider.views.RoundRectOutlineProvider
import com.rapider.views.SelectableTextView
import razerdp.basepopup.BasePopupWindow
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig

class UaPopupHelper(var fragment: PreferenceFragmentCompat,var context:Context) {
    private var uaPopup: BasePopupWindow?= null
    lateinit var uaSelectableTextView: SelectableTextView
    fun showUaPopup() {
        if (uaPopup==null){
            val slideUpIn= AnimationUtils.loadAnimation(context, R.anim.slide_up_in)
            val slideDownOut= AnimationUtils.loadAnimation(context, R.anim.slide_down_out)

            uaPopup= QuickPopupBuilder.with(context)
                    .config(QuickPopupConfig()
                            .gravity(Gravity.BOTTOM)
                            .withShowAnimation(slideUpIn)
                            .withDismissAnimation(slideDownOut)
                            .withClick(R.id.ua_android) {
                                processUa(it)
                            }.withClick(R.id.ua_pc) {
                                processUa(it)
                            }.withClick(R.id.ua_iphone) {
                                processUa(it)
                            })
                    .contentView(R.layout.dialog_ua_setting).build()
            uaPopup?.contentView?.findViewById<View>(R.id.container)?.apply {
                val ua=getUa()
                val iphoneUaTextView=this.findViewById<SelectableTextView>(R.id.ua_iphone)
                if (ua==iphoneUaTextView.text){
                    iphoneUaTextView.select()
                    uaSelectableTextView=iphoneUaTextView
                }else{
                    iphoneUaTextView.unselect()
                }
                val androidUaTextView=this.findViewById<SelectableTextView>(R.id.ua_android)
                if (ua==androidUaTextView.text){
                    androidUaTextView.select()
                    uaSelectableTextView=androidUaTextView
                }else{
                    androidUaTextView.unselect()
                }
                val pcUaTextView=this.findViewById<SelectableTextView>(R.id.ua_pc)
                if (ua==pcUaTextView.text){
                    pcUaTextView.select()
                    uaSelectableTextView=pcUaTextView
                }else{
                    pcUaTextView.unselect()
                }
                clipToOutline=true
                outlineProvider= RoundRectOutlineProvider(context.dp2px(18).toFloat())
            }
        }
        uaPopup?.showPopupWindow()
    }
    private fun processUa(newSelectableTextView: View){
        if (newSelectableTextView==uaSelectableTextView) {
            hideUaDialog()
            return
        }
        uaSelectableTextView.unselect()
        (newSelectableTextView as SelectableTextView).apply {
            this.select()
            uaSelectableTextView=this
            saveUa(this.text)
            hideUaDialog()
            fragment.findPreferenceById(R.string.pref_key_ua_settings_display)?.apply {
                summary=newSelectableTextView.text
            }
        }
    }
    private fun hideUaDialog(){
        uaPopup?.dismiss()
    }

    private  fun getUa():String{
        return context.getSpString(R.string.pref_key_user_agent, context.getString(R.string.ua_android))
    }
    private fun saveUa(ua:String){
        context.setSpString(R.string.pref_key_user_agent,ua)
    }
}