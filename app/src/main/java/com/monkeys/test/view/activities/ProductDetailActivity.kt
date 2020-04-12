package com.monkeys.test.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monkeys.test.R
import com.monkeys.test.model.Product
import com.monkeys.test.view.fragments.ProductDetailFragment

class ProductDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT)
        val productDetailFragment = ProductDetailFragment.newInstance(product)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, productDetailFragment).commit()
    }
}
