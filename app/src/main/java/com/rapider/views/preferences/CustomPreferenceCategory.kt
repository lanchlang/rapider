package com.rapider.views.preferences

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceViewHolder
import com.rapider.R

class CustomPreferenceCategory:PreferenceCategory{
    var titleTv:AppCompatTextView?= null
    constructor(context: Context,attributeSet: AttributeSet):this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet,defStyle:Int):super(context,attributeSet,defStyle){
       layoutResource= R.layout.pref_custom_category
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        titleTv=holder?.itemView?.findViewById(android.R.id.title)
        titleTv?.text=title
    }
}