package com.rapider.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.rapider.R

class CheckboxTextView : FrameLayout {
    var text: String = ""
    lateinit var textView: TextView
    lateinit var iconView: AppCompatImageView
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttr(context, attrs)
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttr(context, attrs)
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initAttr(context, attrs)
        initView(context)
    }

    private fun initAttr(context: Context, attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.CheckBoxTextView).apply {
            text = getString(R.styleable.CheckBoxTextView_checkbox_textview_text) ?: ""
            recycle()
        }
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_text_with_start_icon, this)
        textView=findViewById(R.id.text)
        textView.text=text
        iconView=findViewById(R.id.icon)
    }

    fun select(){
        iconView.setImageResource(R.drawable.ic_check_22)
    }
    fun unselect(){
        iconView.setImageResource(R.drawable.ic_check_disable_22)
    }

}