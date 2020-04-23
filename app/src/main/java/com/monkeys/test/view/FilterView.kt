package com.monkeys.test.view

import android.os.Bundle
import com.monkeys.test.model.filter.Filter
import com.monkeys.test.model.filter.ProductFilter

interface FilterView {
    fun handleArgs(arguments: Bundle?)
    fun setFilters(filter: ProductFilter)
    fun restoreFilters()
    fun saveFilters()
}