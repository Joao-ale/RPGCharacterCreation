package com.fmu.rpgcharactercreation.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fmu.rpgcharactercreation.enum.CharacterClass
import com.fmu.rpgcharactercreation.enum.Origin

@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val characterName: String,
    val playerName: String,
    val appearance: String,
    val personality: String,
    val history: String,
    val attributes: List<Attribute>,
    val origin: Origin,
    val skills: List<Skill>?,
    val characterClass: CharacterClass
)
