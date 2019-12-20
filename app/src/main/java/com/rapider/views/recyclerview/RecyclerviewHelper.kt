package com.rapider.views.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class RecyclerviewHelper {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var itemDecoration: ItemDecoration? = null
    private var touchCallBack: ItemTouchHelper.Callback? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var itemAnimator: RecyclerView.ItemAnimator? = null
    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager): RecyclerviewHelper {
        this.layoutManager = layoutManager
        return this
    }

    fun setItemDecoration(itemDecoration: ItemDecoration): RecyclerviewHelper {
        this.itemDecoration = itemDecoration
        return this
    }

    fun setCallback(callback: ItemTouchHelper.Callback): RecyclerviewHelper {
        this.touchCallBack = callback
        return this
    }

    fun setItemAnimator(itemAnimator: RecyclerView.ItemAnimator): RecyclerviewHelper {
        this.itemAnimator = itemAnimator
        return this
    }

    fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): RecyclerviewHelper {
        this.adapter = adapter
        return this
    }

    fun setup(recyclerView: RecyclerView) {
        recyclerView.layoutManager = layoutManager
                ?: LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        itemDecoration?.apply {
            recyclerView.addItemDecoration(this)
        }
        touchCallBack?.apply {
            ItemTouchHelper(this).attachToRecyclerView(recyclerView)
        }
        itemAnimator?.apply {
            recyclerView.itemAnimator = this
        }
        if (adapter == null) throw Exception()
    }
}