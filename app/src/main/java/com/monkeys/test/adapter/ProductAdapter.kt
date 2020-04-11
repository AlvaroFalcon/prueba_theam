package com.monkeys.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.monkeys.test.R
import com.monkeys.test.model.Product
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(context: Context) : BaseAdapter() {
    private var products = arrayOf<Product>()
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.product_list_item, parent)
        products[position].apply {
            view.title.text = this.name
            view.price.text = convertView?.context?.getString(R.string.product_price_eur, this.finalPrice)
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
    }

}