package com.monkeys.test.model.filter.range_filter

import com.monkeys.test.model.filter.Filter

class RangeFilter(label: String, filterName: String, type: String, val min: Int, val max: Int, val currency: String) : Filter(label, filterName, type)