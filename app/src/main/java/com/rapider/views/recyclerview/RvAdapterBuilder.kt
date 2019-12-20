package com.rapider.views.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
const val ILLEGAL_LAYOUT_RES_ID=-1
class RvAdapterBuilder<T> {
    var layoutResId: Int= ILLEGAL_LAYOUT_RES_ID
    fun setLayoutResId(resId:Int):RvAdapterBuilder<T>{
        this.layoutResId=resId
        return this
    }
    private lateinit var dataList:List<T>
    fun setDataList(list:List<T>):RvAdapterBuilder<T>{
        this.dataList=list
        return this
    }
    private lateinit var helper: ViewHolderHelper<T>
    fun setViewHolderHelper(helper: ViewHolderHelper<T>):RvAdapterBuilder<T>{
        this.helper=helper
        return this
    }
    fun build(): RecyclerView.Adapter<RecyclerView.ViewHolder>{
        return object:RecyclerView.Adapter<RecyclerView.ViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view=LayoutInflater.from(parent.context).inflate(this@RvAdapterBuilder.layoutResId,parent,false)
                return object:RecyclerView.ViewHolder(view){}
            }
            override fun getItemCount(): Int {
                return dataList.size
            }
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                helper.bind(holder,dataList[position])
            }
        }
    }
}

abstract class ViewHolderHelper<T>{
    private lateinit var context: Context
    fun setContext(context: Context):ViewHolderHelper<T>{
        this.context=context
        return this
    }
    fun setLocalImage(viewHolder:RecyclerView.ViewHolder,id:Int,resId:Int):ViewHolderHelper<T>{
        viewHolder.itemView.findViewById<ImageView>(id).setImageResource(resId)
        return this
    }
    //设置Text，可以设置多个参数
    fun setText(viewHolder:RecyclerView.ViewHolder,id:Int,res:Int,vararg args:Any?):ViewHolderHelper<T>{
        viewHolder.itemView.findViewById<TextView>(id).text=context.getString(res).format(args)
        return this
    }
    //设置Text，可以设置多个参数
    fun setHtmlText(viewHolder:RecyclerView.ViewHolder,id:Int,res:Int,vararg args:Any?):ViewHolderHelper<T>{
        val str=context.getString(res).format(args)
        viewHolder.itemView.findViewById<TextView>(id).text= HtmlCompat.fromHtml(str,HtmlCompat.FROM_HTML_MODE_LEGACY)
        return this
    }
    fun setClickListener(viewHolder:RecyclerView.ViewHolder,id:Int,listener: View.OnClickListener):ViewHolderHelper<T>{
        viewHolder.itemView.findViewById<View>(id).setOnClickListener(listener)
        return this
    }
    abstract fun bind(holder:RecyclerView.ViewHolder,data:T)
}