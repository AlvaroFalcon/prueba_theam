package com.monkeys.test.api

import java.lang.reflect.Type

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.monkeys.test.model.filter.Filter
import com.monkeys.test.model.filter.FilterType
import com.monkeys.test.model.filter.image_filter.ImageFilter
import com.monkeys.test.model.filter.range_filter.RangeFilter
import com.monkeys.test.model.filter.text_filter.TextFilter



class FilterSerializer<T: Filter> : JsonDeserializer<T> {
companion object{
    const val TYPE_PROPERTY = "type"
}
    @Throws(JsonParseException::class)
    override fun deserialize(
        elem: JsonElement,
        interfaceType: Type,
        context: JsonDeserializationContext
    ): T {
        val member = elem as JsonObject
        val typeString = get(member, TYPE_PROPERTY)
        val actualType = getClassForType(typeString)

        return context.deserialize(elem, actualType)
    }

    private fun getClassForType(typeElem: JsonElement): Type {
        try {

            val className : String = when(typeElem.asString){
                FilterType.TEXT.value ->{TextFilter::class.java.name}
                FilterType.IMAGE.value ->{ImageFilter::class.java.name}
                FilterType.RANGE.value -> {RangeFilter::class.java.name}
                else ->{""}
            }
            return Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e)
        }
    }

    private operator fun get(wrapper: JsonObject, memberName: String): JsonElement {

        return wrapper.get(memberName)
            ?: throw JsonParseException(
                "no '$memberName' member found in json file."
            )
    }

}