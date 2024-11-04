package com.fmu.rpgcharactercreation.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.fmu.rpgcharactercreation.model.Attribute
import com.fmu.rpgcharactercreation.enum.CharacterClass
import com.fmu.rpgcharactercreation.enum.Origin

class   Converters {
    @TypeConverter
    fun fromAttributesList(value: List<Attribute>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAttributesList(value: String): List<Attribute> {
        val listType = object : TypeToken<List<Attribute>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCharacterClass(value: CharacterClass): String {
        return value.name
    }

    @TypeConverter
    fun toCharacterClass(value: String): CharacterClass {
        return CharacterClass.valueOf(value)
    }

    @TypeConverter
    fun fromOrigin(value: Origin): String {
        return value.name
    }

    @TypeConverter
    fun toOrigin(value: String): Origin {
        return Origin.valueOf(value)
    }
}
