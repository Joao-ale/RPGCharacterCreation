package com.fmu.rpgcharactercreation.model

data class CharacterView(
    val characterId: Long,
    val characterName: String,
    val playerName: String,
    val origin: String,
    val characterClass: String,
    val imageUri: String?,
    val userName: String
)

