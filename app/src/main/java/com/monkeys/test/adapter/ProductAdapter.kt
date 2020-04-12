package com.monkeys.test.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import com.monkeys.test.R
import com.monkeys.test.model.Product
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(val context: Context) : BaseAdapter() {
    private var products = arrayOf<Product>()
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val displayMetrics : DisplayMetrics = context.resources.displayMetrics

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.product_list_item, null)
        products[position].apply {
            view.title.text = this.name
            view.price.text = parent?.context?.getString(R.string.product_price_eur, this.finalPrice)
        }
        if(view != null){
            val cardMargin = context.resources.getDimension(R.dimen.card_margin)
            val cardWidth = (displayMetrics.widthPixels / context.resources.getInteger(R.integer.product_num_columns)) - cardMargin
            val params = LinearLayout.LayoutParams(cardWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT)
            view.layoutParams = params
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return products[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return products.size
    }

    fun refreshData(products : Array<Product>){
        this.products = products
        notifyDataSetChanged()
    }

}