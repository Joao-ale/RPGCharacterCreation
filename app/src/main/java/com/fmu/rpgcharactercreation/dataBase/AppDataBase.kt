package com.fmu.rpgcharactercreation.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fmu.rpgcharactercreation.converters.Converters
import com.fmu.rpgcharactercreation.dao.CharacterDao

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
