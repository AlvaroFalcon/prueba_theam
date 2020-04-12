package com.monkeys.test.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val modelId: String,
    val name: String,
    val type: String,
    val sku: String,
    val description: String,
    val url: String,
    val color : String,
    val composition: String,
    val care: String,
    val originalPrice: Int,
    val finalPrice: Int,
    val finalPriceType: String,
    val currency: String,
    val images: Array<String>,
    val sizes: Array<Size>
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (modelId != other.modelId) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (sku != other.sku) return false
        if (description != other.description) return false
        if (url != other.url) return false
        if (color != other.color) return false
        if (composition != other.composition) return false
        if (care != other.care) return false
        if (originalPrice != other.originalPrice) return false
        if (finalPrice != other.finalPrice) return false
        if (finalPriceType != other.finalPriceType) return false
        if (currency != other.currency) return false
        if (!images.contentEquals(other.images)) return false
        if (!sizes.contentEquals(other.sizes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = modelId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + sku.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + composition.hashCode()
        result = 31 * result + care.hashCode()
        result = 31 * result + originalPrice
        result = 31 * result + finalPrice
        result = 31 * result + finalPriceType.hashCode()
        result = 31 * result + currency.hashCode()
        result = 31 * result + images.contentHashCode()
        result = 31 * result + sizes.contentHashCode()
        return result
    }
}