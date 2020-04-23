package com.monkeys.test.model.filter.text_filter

import com.monkeys.test.model.filter.Filter

class TextFilter(label: String, filterName: String, type: String, val options: Array<TextFilterOption>): Filter(label, filterName, type)