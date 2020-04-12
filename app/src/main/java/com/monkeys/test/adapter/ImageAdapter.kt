package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import kotlinx.android.synthetic.main.product_image_item.view.*

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var itemList: Array<String> = arrayOf()
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getView(),parent,false)
        return ViewHolder(view)
    }

    private fun getView(): Int{
        return R.layout.product_image_item
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.image.setImageResource(R.drawable.placeholder)
    }

    fun refreshData(images: Array<String>) {
        this.itemList = images
        notifyDataSetChanged()
    }
}