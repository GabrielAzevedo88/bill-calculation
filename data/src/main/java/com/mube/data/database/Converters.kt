package com.mube.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class Converters {

    @TypeConverter
    fun fromList(value : List<Int>?) = value?.let { Json.encodeToString(it) }

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<Int>>(value)

}
