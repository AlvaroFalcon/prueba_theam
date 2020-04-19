package com.monkeys.test.model.filter

import java.io.Serializable

class ProductFilter(val storeId: Int, val categoryId: Int, val order: FilterOrder? = null, val page: Int = 1, val limit: Int = 10, val text: String? = null): Serializable