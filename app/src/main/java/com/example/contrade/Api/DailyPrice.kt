package com.example.contrade.Api

import com.beust.klaxon.Json

class DailyPrice(
    private val dailyOpen: Double,
    private val dailyHigh: Double,
    private val dailyLow: Double,
    private val dailyClose: Double,
    private val dailyVolume: Int
) {
    override fun toString(): String {
        return "DailyPrice(dailyOpen=$dailyOpen, dailyHigh=$dailyHigh, dailyLow=$dailyLow, dailyClose=$dailyClose, dailyVolume=$dailyVolume)"
    }
}