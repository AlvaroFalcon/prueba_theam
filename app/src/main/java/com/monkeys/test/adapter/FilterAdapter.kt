package com.monkeys.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appyvet.materialrangebar.RangeBar
import com.monkeys.test.R
import com.monkeys.test.model.filter.Filter
import com.monkeys.test.model.filter.FilterType
import com.monkeys.test.model.filter.image_filter.ImageFilter
import com.monkeys.test.model.filter.range_filter.RangeFilter
import com.monkeys.test.model.filter.text_filter.TextFilter
import kotlinx.android.synthetic.main.range_filter_list_item.view.*
import kotlinx.android.synthetic.main.text_filter_list_item.view.recycler_view

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
        val item = itemList[position]
        holder.itemView.label.text = item.label
        if(item is RangeFilter){
            updatePriceLabels(item.min.toString(), item.max.toString(), holder)
            holder.itemView.price_range.tickStart = item.min.toFloat()
            holder.itemView.price_range.tickEnd = item.max.toFloat()
            holder.itemView.price_range.setRangePinsByValue(item.min.toFloat(), item.max.toFloat())
            holder.itemView.price_range.setOnRangeBarChangeListener(object: RangeBar.OnRangeBarChangeListener{
                override fun onRangeChangeListener(
                    rangeBar: RangeBar?,
                    leftPinIndex: Int,
                    rightPinIndex: Int,
                    leftPinValue: String,
                    rightPinValue: String
                ) {
                    updatePriceLabels(leftPinValue, rightPinValue, holder)
                    updateSelectedValues(leftPinValue, rightPinValue, item)
                }

                override fun onTouchEnded(rangeBar: RangeBar?) {
                }

                override fun onTouchStarted(rangeBar: RangeBar?) {
                }

            })

        }
    }

    private fun updateSelectedValues(
        leftPinValue: String,
        rightPinValue: String,
        item: RangeFilter
    ) {
        item.selectedMin = leftPinValue.toInt()
        item.selectedMax = rightPinValue.toInt()
    }

    private fun updatePriceLabels(
        min: String,
        max: String,
        holder: RangeFilterViewHolder
    ) {
        val context = holder.itemView.context
        holder.itemView.min.text = context.getString(R.string.product_price_eur, min.toInt())
        holder.itemView.max.text = context.getString(R.string.product_price_eur, max.toInt())
    }

    private fun initImageViewHolder(holder: ImageFilterViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.label.text = itemList[position].label
        holder.itemView.recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = holder.adapter
        }

        if(item is ImageFilter){
            holder.adapter.refreshData(item.options)
        }
    }

    private fun initTextViewHolder(holder: TextFilterViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.label.text = item.label
        holder.itemView.recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = holder.adapter
        }
        if(item is TextFilter){
            holder.adapter.refreshData(item.options)
        }
    }

    fun refreshData(availableFilters: Array<Filter>?) {
        availableFilters?.let {
            this.itemList = it
            notifyDataSetChanged()
        }
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class TextFilterViewHolder(view: View) : FilterAdapter.ViewHolder(view){
        val adapter = TextFilterAdapter()
    }
    class ImageFilterViewHolder(view: View): FilterAdapter.ViewHolder(view){
        val adapter = ImageFilterAdapter()
    }
    class RangeFilterViewHolder(view: View): FilterAdapter.ViewHolder(view)

}