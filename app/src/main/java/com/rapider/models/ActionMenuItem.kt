package com.rapider.models

sealed class ActionMenuItem
class IconMenuItem(var localIconResId:Int,var remoteIconUrl:String?,var localLabelResId:Int?,var label:String?,var action:(()->Unit)?=null,var isStable:Boolean=false):ActionMenuItem()
class AddIconMenuItem(var localIconResId: Int,var action:(()->Unit)?=null):ActionMenuItem()
class ToggleMenuItem(var enableIconResId:Int, var disableIconResId:Int, var enableLabelResId:Int=-1,var disableLabelResId:Int=-1, var enable: Boolean=true, var action:(()->Unit)?=null):ActionMenuItem()