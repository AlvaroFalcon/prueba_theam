package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import com.monkeys.test.model.filter.Filter
import com.monkeys.test.model.filter.FilterType
import kotlinx.android.synthetic.main.range_filter_list_item.view.label

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private var itemList: Array<Filter> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getView(viewType),parent,false)
        return getViewHolder(viewType, view)
    }

    private fun getViewHolder(viewType: Int, view: View): ViewHolder {
        return when(viewType){
            FilterType.TEXT.id ->{TextFilterViewHolder(view)}
            FilterType.IMAGE.id ->{ImageFilterViewHolder(view)}
            FilterType.RANGE.id ->{RangeFilterViewHolder(view)}
            else ->{TextFilterViewHolder(view)}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position].type){
            FilterType.TEXT.value ->{FilterType.TEXT.id}
            FilterType.IMAGE.value ->{FilterType.IMAGE.id}
            FilterType.RANGE.value ->{FilterType.RANGE.id}
            else ->{FilterType.TEXT.id}
        }
    }

    private fun getView(viewType: Int): Int{
        return when(viewType){
            FilterType.TEXT.id ->{R.layout.text_filter_list_item}
            FilterType.IMAGE.id ->{R.layout.image_filter_list_item}
            FilterType.RANGE.id ->{R.layout.range_filter_list_item}
            else ->{R.layout.text_filter_list_item}
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is TextFilterViewHolder ->{
                initTextViewHolder(holder, position)
            }
            is ImageFilterViewHolder ->{
                initImageViewHolder(holder, position)
            }
            is RangeFilterViewHolder ->{
                initRangeViewHolder(holder, position)
            }
        }
    }

    private fun initRangeViewHolder(holder: RangeFilterViewHolder, position: Int) {
        holder.itemView.label.text = itemList[position].label
    }

    private fun initImageViewHolder(holder: ImageFilterViewHolder, position: Int) {
        holder.itemView.label.text = itemList[position].label
    }

    private fun initTextViewHolder(holder: TextFilterViewHolder, position: Int) {
        holder.itemView.label.text = itemList[position].label
    }

    fun refreshData(availableFilters: Array<Filter>?) {
        availableFilters?.let {
            this.itemList = it
            notifyDataSetChanged()
        }
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class TextFilterViewHolder(view: View) : FilterAdapter.ViewHolder(view)
    class ImageFilterViewHolder(view: View): FilterAdapter.ViewHolder(view)
    class RangeFilterViewHolder(view: View): FilterAdapter.ViewHolder(view)

}