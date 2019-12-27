package com.rapider.bloc.fragments.settings.font

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.fragment.app.Fragment
import com.rapider.R
import com.rapider.extensions.getSpInt
import com.rapider.extensions.setSpInt
import com.rapider.utils.getFontSizePercent

class FontSettingFragment : Fragment() {
    private lateinit var seekBar: AppCompatSeekBar
    private lateinit var sampleText: TextView
    private lateinit var fontSizeTv: TextView
    private var defaultFontSize: Float = 0f
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_font, container, false)
        seekBar = view.findViewById(R.id.seekbar)
        sampleText = view.findViewById(R.id.sample_text)
        fontSizeTv = view.findViewById(R.id.font_size)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val storeFontSize = requireContext().getSpInt(R.string.pref_key_font_size, 2)
        seekBar.apply {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                min = 0
            }
            max = 4
            progress = storeFontSize

        }
        defaultFontSize=sampleText.textSize/(requireContext().resources.displayMetrics.scaledDensity)
        sampleText.textSize = getFontSizePercent(storeFontSize)*defaultFontSize
        setTextValue(storeFontSize)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                storeValue(progress)
                sampleText.textSize =getFontSizePercent(progress)*defaultFontSize
                setTextValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun setTextValue(size:Int){
        when(size){
            0->{
                fontSizeTv.text=getString(R.string.font_small)
            }
            1->{
                fontSizeTv.text=getString(R.string.font_smaller)
            }
            2->{
                fontSizeTv.text=getString(R.string.font_normal)
            }
            3->{
                fontSizeTv.text=getString(R.string.font_larger)
            }
            4->{
                fontSizeTv.text=getString(R.string.font_large)
            }
        }
    }

    private fun storeValue(value:Int){
        requireContext().setSpInt(R.string.pref_key_font_size,value)
    }

}