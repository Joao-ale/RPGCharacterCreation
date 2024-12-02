package com.fmu.rpgcharactercreation.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.fmu.rpgcharactercreation.enums.CharacterClass
import com.fmu.rpgcharactercreation.enums.Origin

@Entity(
    tableName = "character_table",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Long = 1L,
    val characterName: String,
    val playerName: String,
    val appearance: String,
    val personality: String,
    val history: String,
    val attributes: List<Attribute>,
    val origin: Origin,
    val characterClass: CharacterClass,
    val imageUri: String?,
    val userId: Int = 0
)