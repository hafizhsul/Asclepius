package com.dicoding.asclepius.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context):AppDatabase{
            if (INSTANCE == null){
                synchronized(AppDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "asclepius_database")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}