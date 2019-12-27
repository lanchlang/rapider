package com.rapider.utils

 fun getFontSizePercent( size:Int):Float{
    return when {
        size<2 -> {
            1f + (size - 2) * 0.08f*1f
        }
        size==2 -> {
            1f
        }
        else -> {
            1f + (size - 2) * 0.2f*1f
        }
    }
}