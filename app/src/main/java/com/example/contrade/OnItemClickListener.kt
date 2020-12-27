package com.example.contrade

import com.example.contrade.RoomDatabase.Tranzaction

interface OnItemClickListener {
    fun onItemClick(transaction: Tranzaction?)
    fun onLongItemClick(transaction: Tranzaction?)
}