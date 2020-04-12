package com.monkeys.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.monkeys.test.R
import kotlinx.android.synthetic.main.product_image_item.view.*

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var itemList: Array<String> = arrayOf()
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    companion object{
        const val IMAGES_IN_SCREEN = 2.5f
    }

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
        val context = holder.itemView.context
        setImageSize(context, holder)
        holder.itemView.image.setImageResource(R.drawable.placeholder)
    }

    private fun setImageSize(
        context: Context,
        holder: ViewHolder
    ) {
        val screenWidth = context.resources.displayMetrics.widthPixels
        val margin = context.resources.getDimension(R.dimen.material_horizontal_margin)
        val imgWidth = (screenWidth / IMAGES_IN_SCREEN) - (margin * 2)
        val params =
            LinearLayout.LayoutParams(imgWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT)
        params.marginEnd = margin.toInt()
        holder.itemView.image.layoutParams = params
    }

    fun refreshData(images: Array<String>) {
        this.itemList = images
        notifyDataSetChanged()
    }
}