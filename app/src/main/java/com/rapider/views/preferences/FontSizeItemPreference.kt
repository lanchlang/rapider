package com.rapider.views.preferences

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.preference.PreferenceViewHolder
import androidx.preference.SeekBarPreference
import com.rapider.R


class FontSizeItemPreference:SeekBarPreference{
    var subTitleTv:AppCompatTextView?=null
    var seekbarValue:AppCompatTextView?=null
    var seekbar:AppCompatSeekBar?=null
    var defaultSeekBarValue:String?="kkkkkk"
    var defaultTextSize:Float=0f
    constructor(context: Context,attributeSet: AttributeSet):this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet,defStyle:Int):super(context,attributeSet,defStyle){
       layoutResource= R.layout.pref_font_size_item
    }


    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        subTitleTv=holder?.itemView?.findViewById(android.R.id.summary)
        subTitleTv?.apply {
            defaultTextSize=textSize/(context.resources.displayMetrics.scaledDensity)
            textSize = defaultTextSize*((getPersistedInt(20)).toFloat().div(20))
        }
        seekbarValue=holder?.itemView?.findViewById(R.id.seekbar_value_custom)
        seekbarValue?.apply {
            text=defaultSeekBarValue
        }

        seekbar=holder?.itemView?.findViewById(R.id.seekbar)
        seekbar?.apply {
            if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
                min=10
            }
            max=40
            progress=getPersistedInt(20)
            seekbarValue?.text=(progress*5).toString()+"%"
        }
        seekbar?.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val actualValue = progress * 5
                seekbarValue?.text= "$actualValue%"
                subTitleTv?.apply{
                    textSize = defaultTextSize*((progress).toFloat().div(20))
                }
                persistInt(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

    }

}