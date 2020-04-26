package com.monkeys.test.model.filter.range_filter

import com.monkeys.test.model.filter.Filter

class RangeFilter(
    label: String,
    filterName: String,
    type: String,
    val min: Int,
    val max: Int,
    val currency: String,
    var selectedMin: Int = min,
    var selectedMax: Int = max
) : Filter(label, filterName, type){

    override fun toString(): String {
        return "${getFormattedRange("min", selectedMin)}&filters${getFormattedRange("max", selectedMax)}"
    }

    private fun getFormattedRange(item: String, selectedValue: Int): String{
        return "[$filterName][$item]=${selectedValue}"
    }

}