package com.rapider.views.preferences

import android.app.Activity
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.RadioButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.rapider.R
import com.rapider.extensions.getSpString
import com.rapider.extensions.setSpString

class SingleSelectItemPreference:Preference{
    var checkBox: AppCompatCheckBox?=null
    var selected:Boolean=false
    constructor(context: Context):this(context,null,0)
    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet?,defStyle:Int):super(context,attributeSet,defStyle){
       layoutResource= R.layout.pref_single_select_check_box_item
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        checkBox=holder?.itemView?.findViewById(R.id.single_select_check_box)
        checkBox?.isClickable=false
        checkBox?.isFocusable=false
        checkBox?.isEnabled=false
        checkBox?.isChecked=selected
    }
    fun select(){
        selected=true
        checkBox?.isChecked=true
    }
    fun deselect(){
        selected=false
        checkBox?.isChecked=false
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        if (isPersistent) { // No need to save instance state since it's persistent
            return superState
        }

        val myState = SavedState(superState)
        myState.checked = selected
        return myState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state == null || state.javaClass != SavedState::class.java) { // Didn't save state for us in onSaveInstanceState
            super.onRestoreInstanceState(state)
            return
        }

        val myState = state as SavedState
        super.onRestoreInstanceState(myState.superState)
        selected=myState.checked
        checkBox?.isChecked=selected
    }

}
class SavedState : Preference.BaseSavedState {
    var checked = false

    constructor(source: Parcel) : super(source) {
        checked = source.readInt() == 1
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
        dest.writeInt(if (checked) 1 else 0)
    }

    constructor(superState: Parcelable?) : super(superState) {}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SavedState> {
        override fun createFromParcel(parcel: Parcel): SavedState {
            return SavedState(parcel)
        }

        override fun newArray(size: Int): Array<SavedState?> {
            return arrayOfNulls(size)
        }
    }
}
class SingleSelectItemPreferenceHelper(var activity: Activity, var storeKey:Int,var defaultStoreValue:String){
    private var currentSelectPreference:SingleSelectItemPreference?=null
    var listener:SingleSelectListener?=null
    private var selectedKey:String?=null
    init {
        selectedKey=activity.getSpString(storeKey,defaultStoreValue)
    }
    private fun onSelect(preference:SingleSelectItemPreference?){
        currentSelectPreference?.deselect()
        currentSelectPreference=preference
        currentSelectPreference?.select()
        selectedKey=preference?.key
        activity.setSpString(storeKey,selectedKey ?: defaultStoreValue)
        listener?.onSelect(preference)
    }
    fun register(preference:Preference?){
        preference?.apply {
            if(this.key==selectedKey){
                currentSelectPreference=this as? SingleSelectItemPreference
                (this as? SingleSelectItemPreference)?.select()
            }
        }
        preference?.onPreferenceClickListener= Preference.OnPreferenceClickListener {
            onSelect(it as? SingleSelectItemPreference)
            true
        }
    }
    interface SingleSelectListener{
        fun onSelect(preference: SingleSelectItemPreference?)
    }
}