package com.rapider.bloc.fragments.settings.toolbar

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
import com.rapider.views.CheckboxTextView
import com.rapider.views.RoundRectOutlineProvider
import razerdp.basepopup.BasePopupWindow
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig

class ToolbarStylePopupHelper(var fragment: PreferenceFragmentCompat, var context:Context) {
    private var popup: BasePopupWindow?= null
    lateinit var checkBoxTextView: CheckboxTextView
    fun showPopup() {
        if (popup==null){
            val slideUpIn= AnimationUtils.loadAnimation(context, R.anim.slide_up_in)
            val slideDownOut= AnimationUtils.loadAnimation(context, R.anim.slide_down_out)

            popup= QuickPopupBuilder.with(context)
                    .config(QuickPopupConfig()
                            .gravity(Gravity.BOTTOM)
                            .withShowAnimation(slideUpIn)
                            .withDismissAnimation(slideDownOut)
                            .withClick(R.id.navigation_style_voice_container) {
                                process(it)
                            }.withClick(R.id.navigation_style_light_container) {
                                process(it)
                            }.withClick(R.id.navigation_style_function_container) {
                                process(it)
                            })
                    .contentView(R.layout.dialog_toolbar_mode_setting).build()
            popup?.contentView?.findViewById<View>(R.id.container)?.apply {
                val value= getValue()
                val functionStyleTextView=this.findViewById<CheckboxTextView>(R.id.navigation_style_function)
                if (value==functionStyleTextView.text){
                    functionStyleTextView.select()
                    checkBoxTextView=functionStyleTextView
                }else{
                    functionStyleTextView.unselect()
                }
                val lightStyleTextView=this.findViewById<CheckboxTextView>(R.id.navigation_style_light)
                if (value==lightStyleTextView.text){
                    lightStyleTextView.select()
                    checkBoxTextView=lightStyleTextView
                }else{
                    lightStyleTextView.unselect()
                }
                val voiceStyleTextView=this.findViewById<CheckboxTextView>(R.id.navigation_style_voice)
                if (value==voiceStyleTextView.text){
                    voiceStyleTextView.select()
                    checkBoxTextView=voiceStyleTextView
                }else{
                    voiceStyleTextView.unselect()
                }
                clipToOutline=true
                outlineProvider= RoundRectOutlineProvider(context.dp2px(18).toFloat())
            }
        }
        popup?.showPopupWindow()
    }
    private fun process(container: View){
        container.findViewById<CheckboxTextView>(R.id.navigation_style_light)?.apply {
            processSelectTextView(this)
        }
        container.findViewById<CheckboxTextView>(R.id.navigation_style_function)?.apply {
            processSelectTextView(this)
        }
        container.findViewById<CheckboxTextView>(R.id.navigation_style_voice)?.apply {
            processSelectTextView(this)
        }
    }
    private fun processSelectTextView(newCheckboxTextView: CheckboxTextView){
        if (newCheckboxTextView==checkBoxTextView){
            hideDialog()
            return
        }else{
            saveValue(newCheckboxTextView.text)
            checkBoxTextView.unselect()
            newCheckboxTextView.select()
            checkBoxTextView=newCheckboxTextView
            hideDialog()
            fragment.findPreferenceById(R.string.pref_key_toolbar_style_display)?.apply {
                summary=newCheckboxTextView.text
            }
        }
    }
    private fun hideDialog(){
        popup?.dismiss()
    }

    private  fun getValue():String{
        return context.getSpString(R.string.pref_key_toolbar_style, context.getString(R.string.navigation_style_function))
    }
    private fun saveValue(value:String){
        context.setSpString(R.string.pref_key_toolbar_style,value)
    }
}