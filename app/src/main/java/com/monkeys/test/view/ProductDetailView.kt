package com.monkeys.test.view

import com.monkeys.test.model.Product
import com.monkeys.test.model.Size

interface ProductDetailView {
    interface ProductViewListener {
        fun showInWeb(productUrl: String?)
    }

    fun showProductInformation(product: Product)
    fun showImages(images: Array<String>)
    fun showSizes(sizes: Array<Size>)
    fun showProductNotAvailableError()

}