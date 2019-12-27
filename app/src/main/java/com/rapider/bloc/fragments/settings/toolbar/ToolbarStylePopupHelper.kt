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
import com.rapider.views.RoundRectOutlineProvider
import com.rapider.views.SelectableTextView
import razerdp.basepopup.BasePopupWindow
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig

class ToolbarStylePopupHelper(var fragment: PreferenceFragmentCompat, var context:Context) {
    private var popup: BasePopupWindow?= null
    lateinit var selectableTextView: SelectableTextView
    fun showPopup() {
        if (popup==null){
            val slideUpIn= AnimationUtils.loadAnimation(context, R.anim.slide_up_in)
            val slideDownOut= AnimationUtils.loadAnimation(context, R.anim.slide_down_out)

            popup= QuickPopupBuilder.with(context)
                    .config(QuickPopupConfig()
                            .gravity(Gravity.BOTTOM)
                            .withShowAnimation(slideUpIn)
                            .withDismissAnimation(slideDownOut)
                            .withClick(R.id.always_open) {
                                processPreload(it)
                            }.withClick(R.id.open_in_local_network) {
                                processPreload(it)
                            }.withClick(R.id.never_open) {
                                processPreload(it)
                            })
                    .contentView(R.layout.dialog_pre_load_setting).build()
            popup?.contentView?.findViewById<View>(R.id.container)?.apply {
                val ua=getPreload()
                val alwaysOpenTextView=this.findViewById<SelectableTextView>(R.id.always_open)
                if (ua==alwaysOpenTextView.text){
                    alwaysOpenTextView.select()
                    selectableTextView=alwaysOpenTextView
                }else{
                    alwaysOpenTextView.unselect()
                }
                val openInLocalTextView=this.findViewById<SelectableTextView>(R.id.open_in_local_network)
                if (ua==openInLocalTextView.text){
                    openInLocalTextView.select()
                    selectableTextView=openInLocalTextView
                }else{
                    openInLocalTextView.unselect()
                }
                val neverOpenTextView=this.findViewById<SelectableTextView>(R.id.never_open)
                if (ua==neverOpenTextView.text){
                    neverOpenTextView.select()
                    selectableTextView=neverOpenTextView
                }else{
                    neverOpenTextView.unselect()
                }
                clipToOutline=true
                outlineProvider= RoundRectOutlineProvider(context.dp2px(18).toFloat())
            }
        }
        popup?.showPopupWindow()
    }
    private fun processPreload(newSelectableTextView: View){
        if (newSelectableTextView==selectableTextView) {
            hideUaDialog()
            return
        }
        selectableTextView.unselect()
        (newSelectableTextView as SelectableTextView).apply {
            this.select()
            selectableTextView=this
            savePreload(this.text)
            hideUaDialog()
            fragment.findPreferenceById(R.string.pref_key_pre_load_display)?.apply {
                summary=newSelectableTextView.text
            }
        }
    }
    private fun hideUaDialog(){
        popup?.dismiss()
    }

    private  fun getPreload():String{
        return context.getSpString(R.string.pref_key_pre_load_display, context.getString(R.string.always_open))
    }
    private fun savePreload(preLoad:String){
        context.setSpString(R.string.pref_key_pre_load_display,preLoad)
    }
}