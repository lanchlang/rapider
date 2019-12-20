/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.rapider.views.preferences

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.rapider.R
import com.rapider.extensions.hide
import com.rapider.extensions.show


class CustomPreference : Preference {
    private var titleColor: Int = 0
    private var summaryColor: Int = 0
    private var startIcon:Drawable?=null
    private var endIcon:Drawable?=null
    private var textStartMargin:Float=-1f
    private var customLayoutRes:Int=0
    private var showDivider:Boolean=false
    private var dividerColor:Int=0
    private var childClickListenerMap:HashMap<Int,(View)->Unit> = HashMap()
    private var childLongClickListenerMap:HashMap<Int,(View)->Boolean> = HashMap()
    private var longClickListener:((View)->Boolean)?=null
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs,
        defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.CustomPreference).apply {
            titleColor = getColor(R.styleable.CustomPreference_titleColor,
                ContextCompat.getColor(context, R.color.placeholder_grey))
            summaryColor = getColor(R.styleable.CustomPreference_summaryColor,
                ContextCompat.getColor(context, R.color.placeholder_grey))
            startIcon=getDrawable(R.styleable.CustomPreference_startIcon)
            endIcon=getDrawable(R.styleable.CustomPreference_endIcon)
            textStartMargin=getDimension(R.styleable.CustomPreference_textStartMargin,-1f)
            customLayoutRes=getResourceId(R.styleable.CustomPreference_customLayout,R.layout.pref_custom_item)
            showDivider=getBoolean(R.styleable.CustomPreference_showDivider,false)
            dividerColor=getColor(R.styleable.CustomPreference_dividerColor, ContextCompat.getColor(context, R.color.placeholder_grey))
            recycle()
        }
    }
    fun addChildClickListener(childId:Int,listener:(View)->Unit){
        childClickListenerMap[childId] = listener
    }
    fun addChildLongClickListener(childId:Int,listener:(View)->Boolean){
        childLongClickListenerMap[childId]=listener
    }

    fun addLongClickListener(listener:(View)->Boolean){
        longClickListener=listener
    }
    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.let {

            val containerView=it.itemView
            val titleTv = containerView.findViewById(android.R.id.title) as TextView
            if (title.isNullOrBlank()){
                titleTv.hide()
            }else{
                titleTv.show()
            }
            val summaryTv = containerView.findViewById(android.R.id.summary) as TextView
            if (summary.isNullOrBlank()){
                summaryTv.hide()
            }else{
                summaryTv.show()
            }
            titleTv.setTextColor(titleColor)
            summaryTv.setTextColor(summaryColor)
            //
            (containerView.findViewById(R.id.start_icon) as? AppCompatImageView)?.apply {
                if (startIcon==null){
                    this.hide()
                }else{
                    this.show()
                    setImageDrawable(startIcon)
                }
            }
            //如果需要设置textStartMargin
            if (textStartMargin>=0f){
                containerView.findViewById<View>(R.id.text_container)?.apply {
                    (this.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                        this.marginStart=textStartMargin.toInt()
                    }
                }
            }
            (containerView.findViewById(R.id.end_icon) as? AppCompatImageView)?.apply {
                if (endIcon==null){
                    this.hide()
                }else{
                    this.show()
                    setImageDrawable(endIcon)
                }
            }
            //设置长按事件
            containerView.apply {
                isLongClickable=true
                isClickable=true
                isFocusable=true
                this.setOnLongClickListener(longClickListener)
            }
            //监听子View的点击事件
            childClickListenerMap.forEach {entry->
                containerView.findViewById<View>(entry.key)?.apply {
                    isClickable=true
                    isFocusable=true
                    setOnClickListener(entry.value)
                }
            }
            //监听子View的长按事件
            childLongClickListenerMap.forEach {entry->
                containerView.findViewById<View>(entry.key)?.apply {
                    isClickable=true
                    isFocusable=true
                    isLongClickable=true
                    setOnLongClickListener(entry.value)
                }
            }
            containerView.findViewById<View>(R.id.divider)?.apply{
                if (showDivider){
                    this.show()
                    this.setBackgroundColor(dividerColor)
                }else{
                    this.hide()
                }
            }
        }
    }
}
