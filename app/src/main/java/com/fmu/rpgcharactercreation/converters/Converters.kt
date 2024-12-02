package com.fmu.rpgcharactercreation.converters
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.fmu.rpgcharactercreation.enums.CharacterClass
import com.fmu.rpgcharactercreation.enums.Origin
import com.fmu.rpgcharactercreation.model.Attribute

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromAttributeList(attributes: List<Attribute>): String {
        return gson.toJson(attributes)
    }

    @TypeConverter
    fun toAttributeList(attributesJson: String): List<Attribute> {
        val type = object : TypeToken<List<Attribute>>() {}.type
        return gson.fromJson(attributesJson, type)
    }

    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        return origin.origin
    }

    @TypeConverter
    fun toOrigin(originName: String): Origin {
        return Origin.valueOf(originName)
    }

    @TypeConverter
    fun fromCharacterClass(characterClass: CharacterClass): String {
        return characterClass.description
    }

    @TypeConverter
    fun toCharacterClass(className: String): CharacterClass {
        return CharacterClass.valueOf(className)
    }
}
