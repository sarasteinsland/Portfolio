package com.example.rickandmorty.ui.theme.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.ui.theme.data.Character

// Definerer databasekonfigurasjon og tilknyttede DAO-er
@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao // Tilgang til CharacterDao
    //Kode fra chat
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Henter databaseinstansen eller oppretter den hvis den ikke finnes

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "character-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}