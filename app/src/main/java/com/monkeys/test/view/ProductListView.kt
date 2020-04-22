package com.monkeys.test.view

import com.monkeys.test.common.NetworkOperationCallback
import com.monkeys.test.model.Product
import com.monkeys.test.model.filter.Filter
import com.monkeys.test.model.filter.ProductFilter

interface ProductListView{
    interface ProductSelectionListener{
        fun onProductSelected(product: Product)
    }
    fun showProducts(products: Array<Product>)
    fun setAvailableFilters(filters: Array<Filter>)
    fun applyFilter(filter: ProductFilter)
    val networkOperationCallback: NetworkOperationCallback
}