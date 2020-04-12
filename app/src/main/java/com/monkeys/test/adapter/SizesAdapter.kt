package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import com.monkeys.test.model.Size
import kotlinx.android.synthetic.main.list_item_with_outline.view.*

class SizesAdapter : RecyclerView.Adapter<SizesAdapter.ViewHolder>() {

    private var itemList: Array<Size> = arrayOf()
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
        holder.itemView.text.text = itemList[position].name
    }

    fun refreshData(stores: Array<Size>) {
        this.itemList = stores
        notifyDataSetChanged()
    }
}