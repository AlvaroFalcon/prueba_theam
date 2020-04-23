package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import com.monkeys.test.model.filter.text_filter.TextFilterOption
import kotlinx.android.synthetic.main.list_item_with_outline.view.*

class TextFilterAdapter : RecyclerView.Adapter<TextFilterAdapter.ViewHolder>() {

    private var itemList: Array<TextFilterOption> = arrayOf()
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
        val item = itemList[position]
        val context = holder.itemView.context
        val drawable = ContextCompat.getDrawable(context, if(item.selected) R.drawable.rounded_background_outline_selected else R.drawable.rounded_background_outline)
        holder.itemView.text.text  = item.label
        holder.itemView.background = drawable
        holder.itemView.setOnClickListener {
            item.selected = !item.selected
            notifyItemChanged(position)
        }

    }

    fun refreshData(items: Array<TextFilterOption>) {
        this.itemList = items
        notifyDataSetChanged()
    }
}
