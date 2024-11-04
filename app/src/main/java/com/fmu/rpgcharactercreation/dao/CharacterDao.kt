package com.fmu.rpgcharactercreation.dao

import androidx.room.*
import com.fmu.rpgcharactercreation.model.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<Character>

    @Delete
    suspend fun delete(character: Character)
}
