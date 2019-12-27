/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.rapider.extensions

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils


fun View.hide(): Unit {
    this.visibility=View.GONE
}
fun View.show(){
    this.visibility=View.VISIBLE
}

fun View.isShow():Boolean{
    return this.visibility==View.VISIBLE
}

fun View.isHide():Boolean{
    return this.visibility==View.GONE
}
fun View.startAnimationWithEndCallBack(animationId:Int, callback:()->Unit){
    this.show()
    val animation = AnimationUtils.loadAnimation(
            this.context,
            animationId
    ).apply {
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                callback.invoke()
            }

            override fun onAnimationStart(animation: Animation?) {}
        })
    }
    this.startAnimation(animation)
}