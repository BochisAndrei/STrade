package com.example.contrade.RoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application)  {
    private val repositoryTransaction: TranzactionRepository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allTransactions : LiveData<List<Tranzaction>>

    init {
        val tranzactionDao = TradeRoomDatabase.getDatabase(application, viewModelScope).tranzactionDao()
        repositoryTransaction = TranzactionRepository(tranzactionDao)
        allTransactions = repositoryTransaction.allTranzactions
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(transaction: Tranzaction) = viewModelScope.launch(Dispatchers.IO) {
        repositoryTransaction.insert(transaction)
    }
}