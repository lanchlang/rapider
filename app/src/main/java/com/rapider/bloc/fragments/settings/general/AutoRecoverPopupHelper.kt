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

class AutoRecoverPopupHelper(var fragment: PreferenceFragmentCompat, var context:Context) {
    private var autoRecoverPopup: BasePopupWindow?= null
    lateinit var autoRecoverSelectableTextView: SelectableTextView
    fun showAutoRecoverPopup() {
        if (autoRecoverPopup==null){
            val slideUpIn= AnimationUtils.loadAnimation(context, R.anim.slide_up_in)
            val slideDownOut= AnimationUtils.loadAnimation(context, R.anim.slide_down_out)

            autoRecoverPopup= QuickPopupBuilder.with(context)
                    .config(QuickPopupConfig()
                            .gravity(Gravity.BOTTOM)
                            .withShowAnimation(slideUpIn)
                            .withDismissAnimation(slideDownOut)
                            .withClick(R.id.no_auto_recovery) {
                                processAutoRecover(it)
                            }.withClick(R.id.ask_before_auto_recovery) {
                                processAutoRecover(it)
                            }.withClick(R.id.auto_recovery) {
                                processAutoRecover(it)
                            })
                    .contentView(R.layout.dialog_auto_recovery_setting).build()
            autoRecoverPopup?.contentView?.findViewById<View>(R.id.container)?.apply {
                val autoRecover= getValue()
                val autoRecoveryTextView=this.findViewById<SelectableTextView>(R.id.auto_recovery)
                if (autoRecover==autoRecoveryTextView.text){
                    autoRecoveryTextView.select()
                    autoRecoverSelectableTextView=autoRecoveryTextView
                }else{
                    autoRecoveryTextView.unselect()
                }
                val askBeforeAutoRecoveryTextView=this.findViewById<SelectableTextView>(R.id.ask_before_auto_recovery)
                if (autoRecover==askBeforeAutoRecoveryTextView.text){
                    askBeforeAutoRecoveryTextView.select()
                    autoRecoverSelectableTextView=askBeforeAutoRecoveryTextView
                }else{
                    askBeforeAutoRecoveryTextView.unselect()
                }
                val noAutoRecoveryTextView=this.findViewById<SelectableTextView>(R.id.no_auto_recovery)
                if (autoRecover==noAutoRecoveryTextView.text){
                    noAutoRecoveryTextView.select()
                    autoRecoverSelectableTextView=noAutoRecoveryTextView
                }else{
                    noAutoRecoveryTextView.unselect()
                }
                clipToOutline=true
                outlineProvider= RoundRectOutlineProvider(context.dp2px(18).toFloat())
            }
        }
        autoRecoverPopup?.showPopupWindow()
    }
    private fun processAutoRecover(newSelectableTextView: View){
        if (newSelectableTextView==autoRecoverSelectableTextView) {
            hideDialog()
            return
        }
        autoRecoverSelectableTextView.unselect()
        (newSelectableTextView as SelectableTextView).apply {
            this.select()
            autoRecoverSelectableTextView=this
            saveValue(this.text)
            hideDialog()
            fragment.findPreferenceById(R.string.pref_key_auto_recover)?.apply {
                val value=newSelectableTextView.text
                if (value==context.getString(R.string.auto_recovery) || value==context.getString(R.string.ask_before_auto_recovery)){
                    this.summary=context.getString(R.string.open)
                }else{
                    this.summary=context.getString(R.string.close)
                }
            }
        }
    }
    private fun hideDialog(){
        autoRecoverPopup?.dismiss()
    }

    private  fun getValue():String{
        return context.getSpString(R.string.pref_key_auto_recover, context.getString(R.string.auto_recovery))
    }
    private fun saveValue(value:String){
        context.setSpString(R.string.pref_key_auto_recover,value)
    }
}