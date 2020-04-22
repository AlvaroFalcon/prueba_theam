package com.monkeys.test.model.filter

import java.io.Serializable

class ProductFilter(
    val storeId: Int,
    val categoryId: Int,
    var order: FilterOrder? = null,
    val page: Int = 1,
    val limit: Int = 10,
    var text: String? = null,
    var availableFilters: Array<Filter>? = null
) : Serializable