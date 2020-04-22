package com.monkeys.test.model.filter.image_filter

import java.io.Serializable

class ImageFilterOption(
    val id: String,
    val label: String,
    val imageUrl: String,
    var selected: Boolean? = false
) : Serializable