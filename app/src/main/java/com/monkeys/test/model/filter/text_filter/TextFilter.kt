package com.monkeys.test.model.filter.text_filter

import com.monkeys.test.model.filter.Filter

class TextFilter(
    label: String,
    filterName: String,
    type: String,
    val options: Array<TextFilterOption>
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