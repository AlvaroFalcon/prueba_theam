package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import com.monkeys.test.common.GlideApp
import com.monkeys.test.model.filter.image_filter.ImageFilterOption
import kotlinx.android.synthetic.main.simple_image_list_item.view.*

class ImageFilterAdapter : RecyclerView.Adapter<ImageFilterAdapter.ViewHolder>() {

    private var itemList: Array<ImageFilterOption> = arrayOf()
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getView(),parent,false)
        return ViewHolder(view)
    }

    private fun getView(): Int{
        return R.layout.simple_image_list_item
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.setOnClickListener {
            item.selected = !item.selected
            notifyItemChanged(position)
        }
        val markerVisibility = if(item.selected) View.VISIBLE else View.GONE
        holder.itemView.selected_marker.visibility = markerVisibility
        GlideApp.with(holder.itemView.image.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.placeholder)
            .fitCenter()
            .into(holder.itemView.image)
    }

    fun refreshData(items: Array<ImageFilterOption>) {
        this.itemList = items
        notifyDataSetChanged()
    }
}