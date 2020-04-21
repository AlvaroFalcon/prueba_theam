package com.monkeys.test.model.filter.image_filter

import com.monkeys.test.model.filter.Filter

class ImageFilter(label: String, filterName: String, type: String, val options: Array<ImageFilterOption>) : Filter(label, filterName, type)