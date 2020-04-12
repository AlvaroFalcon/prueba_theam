package com.monkeys.test.presenter

import android.os.Bundle
import com.monkeys.test.model.Product
import com.monkeys.test.view.ProductDetailView
import com.monkeys.test.view.activities.ProductDetailActivity.Companion.EXTRA_PRODUCT

class ProductDetailPresenter(val productDetailView: ProductDetailView?) {
    var product : Product? = null
    fun handleArgs(arguments: Bundle?){
        getArguments(arguments)
        product?.let {
            this.productDetailView?.showProductInformation(it)
        }?: productDetailView?.showProductNotAvailableError()
    }

    private fun getArguments(arguments: Bundle?) {
        if (arguments != null && arguments.containsKey(EXTRA_PRODUCT)) {
            this.product = arguments.getParcelable(EXTRA_PRODUCT)
        }
    }
}