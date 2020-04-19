package com.monkeys.test.model.filter

import android.content.Context
import com.monkeys.test.R
import java.io.Serializable

enum class ORDER_BY(val value: String){NAME("name"), BEST_SELLER("bestsellers"), PRICE("price")}
enum class ORDER_DIR(val value: String){ASC("asc"), DESC("desc")}

class FilterOrder (val orderBy: ORDER_BY, val orderDir: ORDER_DIR, val title: String): Serializable{
    companion object{
        fun getFilterOrderList(context: Context): Array<FilterOrder>{
            return arrayOf(
                FilterOrder(ORDER_BY.NAME, ORDER_DIR.DESC, context.getString(R.string.filter_by_name_desc)),
                FilterOrder(ORDER_BY.NAME, ORDER_DIR.ASC, context.getString(R.string.filter_by_name_asc)),
                FilterOrder(ORDER_BY.PRICE, ORDER_DIR.DESC, context.getString(R.string.filter_by_price_desc)),
                FilterOrder(ORDER_BY.PRICE, ORDER_DIR.ASC, context.getString(R.string.filter_by_price_asc)),
                FilterOrder(ORDER_BY.BEST_SELLER, ORDER_DIR.DESC, context.getString(R.string.filter_by_bestseller_desc))
            )
        }
    }

    override fun toString(): String {
        return title
    }
}