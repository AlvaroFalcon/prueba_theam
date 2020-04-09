package com.monkeys.test.model

data class SearchResultResponse(val results: Array<Product>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchResultResponse

        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        return results.contentHashCode()
    }
}