package com.monkeys.test.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monkeys.test.R
import com.monkeys.test.model.Category
import com.monkeys.test.model.Product
import com.monkeys.test.view.CategoryView
import com.monkeys.test.view.ProductListView
import com.monkeys.test.view.fragments.CategoryFragment
import com.monkeys.test.view.fragments.ProductFragment

class MainActivity : AppCompatActivity(), CategoryView.CategorySelectionListener, ProductListView.ProductSelectionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CategoryFragment.newInstance()).commit()
        }

    }

    override fun onCategorySelected(category: Category) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductFragment.newInstance(category))
            .addToBackStack("${CategoryFragment::class.java.name}${category.categoryId}")
            .commit()
    }

    override fun onProductSelected(product: Product) {
    }
}
