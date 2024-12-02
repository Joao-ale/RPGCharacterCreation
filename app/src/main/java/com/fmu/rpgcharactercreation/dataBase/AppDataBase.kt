package com.fmu.rpgcharactercreation.dataBase

import com.fmu.rpgcharactercreation.dao.CharacterDao
import com.fmu.rpgcharactercreation.model.Character
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fmu.rpgcharactercreation.converters.Converters
import com.fmu.rpgcharactercreation.dao.UserDao
import com.fmu.rpgcharactercreation.model.User

@Database(entities = [Character::class, User::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun userDao(): UserDao
}
