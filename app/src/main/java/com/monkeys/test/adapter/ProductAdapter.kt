package com.monkeys.test.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.monkeys.test.R
import com.monkeys.test.model.Product
import com.monkeys.test.view.ProductListView
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(
    val context: Context,
    val listener: ProductListView.ProductSelectionListener?
) : BaseAdapter() {
    private var products = arrayOf<Product>()
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: CardViewHolder
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.product_list_item, null)
            holder = CardViewHolder(view)
            view.tag = holder
            configureCardSize(view)
        } else {
            view = convertView
            holder = convertView.tag as CardViewHolder
        }
        products[position].apply {
            holder.titleTextView.text = this.name
            holder.priceTextView.text =
                parent?.context?.getString(R.string.product_price_eur, this.finalPrice)
            holder.card.setOnClickListener {
                this@ProductAdapter.listener?.onProductSelected(this)
            }
        }
        return view
    }

    private fun configureCardSize(view: View) {
        val cardMargin = context.resources.getDimension(R.dimen.card_margin)
        val cardWidth =
            (displayMetrics.widthPixels / context.resources.getInteger(R.integer.product_num_columns)) - (cardMargin * 2)
        val params =
            LinearLayout.LayoutParams(cardWidth.toInt(), view.card.layoutParams.height)
        view.layoutParams = params
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

    fun refreshData(products: Array<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class CardViewHolder(val view: View) {
        val image: ImageView = view.image
        val priceTextView: TextView = view.price
        val titleTextView: TextView = view.title
        val card: CardView = view.card
    }

}