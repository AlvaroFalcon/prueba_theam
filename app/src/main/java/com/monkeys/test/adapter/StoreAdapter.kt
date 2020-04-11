package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import com.monkeys.test.model.Store
import com.monkeys.test.view.StoreSelectionView
import kotlinx.android.synthetic.main.simple_list_item.view.*

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    private var itemList: Array<Store> = arrayOf()
    var listener: StoreSelectionView.StoreSelectionListener? = null
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
        holder.itemView.setOnClickListener { listener?.onStoreSelected(itemList[position]) }
    }

    fun refreshData(stores: Array<Store>) {
        this.itemList = stores
        notifyDataSetChanged()
    }

}