package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import com.monkeys.test.model.filter.image_filter.ImageFilterOption

class ImageFilterAdapter : RecyclerView.Adapter<ImageFilterAdapter.ViewHolder>() {

    private var itemList: Array<ImageFilterOption> = arrayOf()
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getView(),parent,false)
        return ViewHolder(view)
    }

    private fun getView(): Int{
        return R.layout.list_item_with_outline
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    fun refreshData(sizes: Array<ImageFilterOption>) {
        this.itemList = sizes
        notifyDataSetChanged()
    }
}