package com.fmu.rpgcharactercreation.dataBase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "rpg_database"
                ).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        db.execSQL("CREATE VIEW IF NOT EXISTS character_view AS SELECT * FROM character_table")
                    }
                }).build()
            }
        }
        return INSTANCE!!
    }
}
