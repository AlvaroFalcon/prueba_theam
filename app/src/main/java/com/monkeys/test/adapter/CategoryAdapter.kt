package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import com.monkeys.test.model.Category
import com.monkeys.test.model.Store
import com.monkeys.test.view.CategoryView
import kotlinx.android.synthetic.main.simple_list_item.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var itemList: Array<Category> = arrayOf()
    var listener: CategoryView.CategorySelectionListener? = null
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text.text = itemList[position].name
        holder.itemView.setOnClickListener { listener?.onCategorySelected(itemList[position]) }
    }

    fun refreshData(stores: Array<Category>) {
        this.itemList = stores
        notifyDataSetChanged()
    }
}