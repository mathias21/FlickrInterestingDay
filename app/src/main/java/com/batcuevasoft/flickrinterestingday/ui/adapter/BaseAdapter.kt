package com.batcuevasoft.flickrinterestingday.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<R : StableIdElement, T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    private var mutableList = mutableListOf<R>()
        set(value) {
            this.mutableList.clear()
            this.mutableList.addAll(value)
            notifyDataSetChanged()
        }

    var elementList: List<R>
        set(value) {
            mutableList = value.toMutableList()
        }
        get() {
            return mutableList.toList()
        }


    init {
        this.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return mutableList[position].getReusableId()
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    fun addItem(item: R) {
        mutableList.add(item)
        notifyItemInserted(mutableList.lastIndex)
    }

}

interface StableIdElement {
    fun getReusableId(): Long
}