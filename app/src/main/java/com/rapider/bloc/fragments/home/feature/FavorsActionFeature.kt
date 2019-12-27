package com.rapider.bloc.fragments.home.feature


import android.graphics.Rect
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import com.rapider.R
import com.rapider.bloc.fragments.home.HomeFragment
import com.rapider.bloc.fragments.home.feature.FavorActionAdapter.FavorActionAdapter.ADD_ACTION_ITEM
import com.rapider.bloc.fragments.home.feature.FavorActionAdapter.FavorActionAdapter.ICON_ACTION_ITEM
import com.rapider.bloc.fragments.home.feature.FavorActionAdapter.FavorActionAdapter.ILLEGAL
import com.rapider.extensions.*
import com.rapider.models.ActionMenuItem
import com.rapider.models.AddIconMenuItem
import com.rapider.models.IconMenuItem
import com.rapider.views.outboundlistener.CoordinatorLayoutWithOutBoundListener
import com.rapider.views.outboundlistener.OutBoundClickListener
import com.rapider.views.recyclerview.GridSpacingItemDecoration
import com.rapider.views.recyclerview.SimpleItemTouchCallBack
import com.rapider.views.recyclerview.TouchCallBack

import mozilla.components.support.base.feature.BackHandler
import mozilla.components.support.base.feature.LifecycleAwareFeature
import kotlin.collections.ArrayList

class FavorsActionFeature(var fragment: HomeFragment) : LifecycleAwareFeature,
        OutBoundClickListener, BackHandler {

    var recyclerView: RecyclerView? = null
    private var favorActions: ArrayList<ActionMenuItem> = ArrayList()
    var mAdapter: FavorActionAdapter
    var showAddIcon = false

    init {
        favorActions.add(
            IconMenuItem(
                R.drawable.ic_logo_pickup_108,
                null,
                R.string.pickup,
                null,
                null,
                true
            )
        )
        favorActions.add(
            IconMenuItem(
                R.drawable.ic_logo_jingdong_108,
                null,
                R.string.jingdong,
                null
            )
        )
        favorActions.add(IconMenuItem(R.drawable.ic_logo_taobao_108, null, R.string.taobao, null))
        favorActions.add(IconMenuItem(R.drawable.ic_logo_tmall_108, null, R.string.tmall, null))
        favorActions.add(
            IconMenuItem(
                R.drawable.ic_logo_weipin_108,
                null,
                R.string.weipinhui,
                null
            )
        )
        favorActions.add(IconMenuItem(R.drawable.ic_logo_weibo_108, null, R.string.weibo, null))
        favorActions.add(IconMenuItem(R.drawable.ic_logo_zhihu_108, null, R.string.zhihu, null))
        favorActions.add(
            IconMenuItem(
                R.drawable.ic_logo_bilibili_108,
                null,
                R.string.bilibili,
                null
            )
        )
        favorActions.add(IconMenuItem(R.drawable.ic_logo_baidu_108, null, R.string.baidu, null))
        favorActions.add(AddIconMenuItem(R.drawable.ic_add_108, null))
        mAdapter = FavorActionAdapter(favorActions = favorActions)
        //mAdapter.setHasStableIds(true)
        recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.favors)?.apply {
            layoutManager = GridLayoutManager(fragment.requireContext(), 5)
            addItemDecoration(
                GridSpacingItemDecoration(
                    5,
                    context.dp2px(10),
                        context.dp2px(3),
                    false
                )
            )
            adapter = mAdapter
        }
        val simpleItemTouchCallBack = SimpleItemTouchCallBack(object : TouchCallBack {
            override fun onItemMove(fromPosition: Int, toPosition: Int) {
                val fromItem = favorActions[fromPosition] as? IconMenuItem
                val toItem = favorActions[toPosition] as? IconMenuItem
                if (fromItem == null || toItem == null) {
                    return
                }
                if (fromPosition == toPosition || fromItem.isStable || toItem.isStable) return
                if (fromPosition > toPosition) {
                    val lastItem = favorActions[fromPosition]
                    var index = fromPosition
                    while (index > toPosition) {
                        favorActions[index] = favorActions[index - 1]
                        index--
                    }
                    favorActions[toPosition] = lastItem
                } else if (fromPosition < toPosition) {
                    val firstItem = favorActions[fromPosition]
                    var index = fromPosition
                    while (index < toPosition) {
                        favorActions[index] = favorActions[index + 1]
                        index++
                    }
                    favorActions[toPosition] = firstItem
                }
                //刷新界面,局部刷新,索引会混乱
                recyclerView?.adapter?.notifyItemMoved(fromPosition, toPosition)
            }

            override fun onItemDelete(position: Int) {}
        })
        simpleItemTouchCallBack.setmSwipeEnable(false)
        val helper = ItemTouchHelper(simpleItemTouchCallBack)
        helper.attachToRecyclerView(recyclerView)
        fragment.view?.findViewById<CoordinatorLayoutWithOutBoundListener>(R.id.container)
            ?.setOutBoundClickListener(this)
    }

    override fun start() {

    }

    override fun stop() {

    }

    override fun needDetectOutBoundClick(): Boolean {
        return mAdapter.showDeleteIcon
    }

    override fun onOutBoundClick(event: MotionEvent) {
        if (event.actionMasked == MotionEvent.ACTION_UP) {
            mAdapter.hideDeleteIcon()
        }
        when (event.actionMasked) {
            MotionEvent.ACTION_CANCEL -> {
                resetOutBoundListenerState()
            }
            MotionEvent.ACTION_UP -> {
                resetOutBoundListenerState()
            }
        }
    }
    private fun resetOutBoundListenerState(){
        isDownEventOutBound=false
    }
    private var isDownEventOutBound = false
    override fun isDownEventInOutBound(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                recyclerView?.apply {
                    val rec = Rect()
                    this.getGlobalVisibleRect(rec)
                    isDownEventOutBound = !rec.contains(event.rawX.toInt(), event.rawY.toInt())
                }
            }
        }
        return isDownEventOutBound
    }

    override fun onBackPressed(): Boolean {
        if (needDetectOutBoundClick()) {
            mAdapter.hideDeleteIcon()
            return true
        }
        return false
    }
}

class FavorActionVH(var view: View) : RecyclerView.ViewHolder(view) {
    var iconView: ImageView? = null
    var labelTextView: TextView? = null
    var closeView: ImageView? = null

    init {
        iconView = view.findViewById(R.id.icon)
        labelTextView = view.findViewById(R.id.label)
        closeView = view.findViewById(R.id.delete)
    }
}

class FavorActionAdapter(private var favorActions: ArrayList<ActionMenuItem>) :
    RecyclerView.Adapter<FavorActionVH>() {
    object FavorActionAdapter {
        const val ADD_ACTION_ITEM = 0
        const val ICON_ACTION_ITEM = 1
        const val ILLEGAL=-1
    }

    var showDeleteIcon: Boolean = false
    private var showDeleteIconAnimation: Boolean = false
    private var hideDeleteIconAnimation: Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorActionVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favor_action, parent, false)
        return FavorActionVH(view)
    }

    override fun getItemCount(): Int {
        return favorActions.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (favorActions[position]) {
            is AddIconMenuItem -> ADD_ACTION_ITEM
            is IconMenuItem -> ICON_ACTION_ITEM
            else -> ILLEGAL
        }
    }

    override fun onBindViewHolder(holder: FavorActionVH, position: Int) {
        when (val item = favorActions[position]) {
            is AddIconMenuItem -> {
                holder.iconView?.apply {
                    setImageResource(item.localIconResId)
                    setOnClickListener {
                        addAction(
                            IconMenuItem(
                                R.drawable.ic_logo_taobao_108,
                                null,
                                R.string.taobao,
                                null
                            )
                        )
                    }
                }
                holder.iconView?.hide()
                holder.labelTextView?.text = ""
                holder.view.isClickable = true
                holder.view.setOnClickListener {
                    holder.iconView?.apply {
                        if (this.isHide()) {
                            this.startAnimationWithEndCallBack(R.anim.fade_in){
                                holder.iconView?.startAnimationWithEndCallBack(R.anim.fade_out){
                                    holder.iconView?.hide()
                                }
                            }
                        }
                    }
                }
            }
            is IconMenuItem -> {
                holder.labelTextView?.text = holder.view.context.getString(item.localLabelResId!!)
                holder.iconView?.setImageResource(item.localIconResId)
                holder.iconView?.show()
                holder.closeView?.apply {
                    isClickable = true
                    isFocusable = true
                    setOnClickListener {
                        deleteAction(item)
                    }
                }
                holder.view.isLongClickable = true
                holder.view.isClickable = true
                holder.view.isFocusable = true
                holder.view.setOnLongClickListener {
                    showDeleteIcon()
                    true
                }
                holder.view.setOnClickListener {
                    item.action?.invoke()
                }
                if (!item.isStable) {
                    if (showDeleteIcon) {
                        if (showDeleteIconAnimation) {
                            holder.closeView?.startAnimationWithEndCallBack(R.anim.scale_up) {showDeleteIconAnimation=false}
                        } else {
                            holder.closeView?.apply {
                                show()
                            }
                        }
                    } else {
                        if (hideDeleteIconAnimation) {
                            holder.closeView?.startAnimationWithEndCallBack(R.anim.scale_down){
                                hideDeleteIconAnimation = false
                                holder.closeView?.hide()
                            }
                        } else {
                            holder.closeView?.apply {
                                hide()
                            }
                        }
                    }
                } else {
                    holder.closeView?.hide()
                }
            }
        }

    }

    fun showDeleteIcon() {
        if (showDeleteIcon) return
        showDeleteIcon = true
        if (!showDeleteIconAnimation) {
            showDeleteIconAnimation = true
        }
        notifyDataSetChanged()
    }

    fun hideDeleteIcon() {
        if (!showDeleteIcon) return
        showDeleteIcon = false
        if (!hideDeleteIconAnimation) {
            hideDeleteIconAnimation = true
        }
        notifyDataSetChanged()
    }

    fun deleteAction(item: IconMenuItem) {
        val newIndex = favorActions.indexOf(item)
        favorActions.remove(item)
        notifyItemRemoved(newIndex)
        val size = favorActions.size
        if (size < 10 && favorActions.last() !is AddIconMenuItem) {
            favorActions.add(AddIconMenuItem(R.drawable.ic_add_108, null))
            notifyItemInserted(favorActions.size - 1)
        }
    }

    fun addAction(iconMenuItem: IconMenuItem) {
        //还没有填满
        val lastItem = favorActions.last()
        if (lastItem is AddIconMenuItem) {
            val size = favorActions.size
            if (size == 10) {
                val index = favorActions.indexOf(lastItem)
                favorActions[index] = iconMenuItem
                notifyItemChanged(index)
            }
            if (size < 10) {
                favorActions.add(size - 1, iconMenuItem)
                notifyItemInserted(size - 1)
            }
        }
    }
}