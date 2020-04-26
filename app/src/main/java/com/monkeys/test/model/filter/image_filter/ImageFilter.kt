package com.monkeys.test.model.filter.image_filter

import com.monkeys.test.model.filter.Filter

class ImageFilter(
    label: String,
    filterName: String,
    type: String,
    val options: Array<ImageFilterOption>
) : Filter(label, filterName, type) {

    override fun toString(): String {
        val formattedOptions = getFormattedOptions()
        if (formattedOptions.isEmpty()) return ""
        return "[$filterName][]=$formattedOptions"
    }

    private fun getFormattedOptions(): String {
        var formattedOptions = ""
        val selectedOptions = options.filter { it.selected }
        if (selectedOptions.isEmpty()) return formattedOptions
        for (i in selectedOptions.indices) {
            formattedOptions += selectedOptions[i].id
            if (i != selectedOptions.indices.last) formattedOptions += ","
        }
        return formattedOptions
    }
}