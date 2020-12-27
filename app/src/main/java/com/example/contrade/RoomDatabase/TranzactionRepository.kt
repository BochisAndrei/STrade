package com.example.contrade.RoomDatabase

import androidx.lifecycle.LiveData

class TranzactionRepository(private val folderDao: TranzactionDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTranzactions: LiveData<List<Tranzaction>> = folderDao.getOrderedTranzactions()

    suspend fun insert(tranzaction: Tranzaction) {
        folderDao.insert(tranzaction)
    }
}