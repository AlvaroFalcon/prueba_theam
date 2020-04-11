package com.monkeys.test.view

import com.monkeys.test.model.Product

interface ProductListView{
    interface ProductSelectionListener{
        fun onProductSelected(product: Product)
    }
    fun showProducts(products: Array<Product>)
}