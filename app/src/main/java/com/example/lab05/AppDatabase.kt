package com.example.lab05

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun lstDao(): ListDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        @Synchronized
        open fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database.db"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}