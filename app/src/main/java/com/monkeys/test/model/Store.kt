package com.monkeys.test.model

data class Store(val name: String, val countryCode: String, val storeCode: String, val websiteCode: String, val storeViews: Array<StoreView> ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Store

        if (name != other.name) return false
        if (countryCode != other.countryCode) return false
        if (storeCode != other.storeCode) return false
        if (websiteCode != other.websiteCode) return false
        if (!storeViews.contentEquals(other.storeViews)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + countryCode.hashCode()
        result = 31 * result + storeCode.hashCode()
        result = 31 * result + websiteCode.hashCode()
        result = 31 * result + storeViews.contentHashCode()
        return result
    }
}