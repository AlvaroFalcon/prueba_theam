package com.monkeys.test.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(val categoryId: Int, val name : String, val children : Array<Category>) :
    Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (categoryId != other.categoryId) return false
        if (name != other.name) return false
        if (!children.contentEquals(other.children)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = categoryId
        result = 31 * result + name.hashCode()
        result = 31 * result + children.contentHashCode()
        return result
    }
}