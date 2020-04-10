package com.monkeys.test.view

import com.monkeys.test.model.Category


interface CategoryView {

    interface CategorySelectionListener {
        fun onCategorySelected(category: Category)
    }

    fun showCategories(categories: Array<Category>)

}