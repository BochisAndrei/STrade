package com.example.contrade.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TranzactionDao {

    @Insert
    suspend fun insert(tranzaction: Tranzaction)

    @Update
    suspend fun update(tranzaction: Tranzaction)

    @Delete
    suspend fun delete(tranzaction: Tranzaction)

    @Query("SELECT * from tranzactie_table ORDER BY id ASC")
    fun getOrderedTranzactions(): LiveData<List<Tranzaction>>
}