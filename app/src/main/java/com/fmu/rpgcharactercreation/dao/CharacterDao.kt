package com.fmu.rpgcharactercreation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.fmu.rpgcharactercreation.model.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Query("SELECT * FROM character_table")
    suspend fun getAllCharacters(): List<Character>

    @Query("SELECT * FROM character_table WHERE userId = :userId")
    suspend fun getCharactersByUser(userId: Int): List<Character>

    @Delete
    suspend fun delete(character: Character)
}
