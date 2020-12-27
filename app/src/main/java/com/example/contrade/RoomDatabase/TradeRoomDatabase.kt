package com.example.contrade.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Tranzaction::class], version = 1, exportSchema = false)
abstract class TradeRoomDatabase : RoomDatabase() {

    abstract fun tranzactionDao(): TranzactionDao

    private class NoteDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabaseOnCreate(database.tranzactionDao())
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabaseOnOpen(database.tranzactionDao())
                }
            }
        }

        suspend fun populateDatabaseOnCreate(transactionDao: TranzactionDao) {
            // Add sample words.
            var tranzation = Tranzaction("something", "something", "something", 2,3.1,"",true)
            transactionDao.insert(tranzation)
        }

        suspend fun populateDatabaseOnOpen(transactionDao: TranzactionDao) {
//            var tranzation = Tranzaction("something", "something", "something", 2,3.1,"",true)
//            transactionDao.insert(tranzation)
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TradeRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TradeRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TradeRoomDatabase::class.java,
                        "notes_database"
                )
                        .addCallback(NoteDatabaseCallback(scope))
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}