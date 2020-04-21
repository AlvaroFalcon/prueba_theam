package com.monkeys.test.model

import com.monkeys.test.model.filter.Filter

data class SearchResultResponse(val results: Array<Product>, val filters: Array<Filter>, val resultCount: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchResultResponse

        if (!results.contentEquals(other.results)) return false
        if (!filters.contentEquals(other.filters)) return false
        if (resultCount != other.resultCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = results.contentHashCode()
        result = 31 * result + filters.contentHashCode()
        result = 31 * result + resultCount
        return result
    }

}