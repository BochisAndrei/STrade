package com.example.contrade.Api

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DailyPrice(
        val dailyOpen: Double,
        val dailyHigh: Double,
        val dailyLow: Double,
        val dailyClose: Double,
        val dailyVolume: Int
) {
    lateinit var day: LocalDate
    var hour: Int = 0
    var minute: Int = 0
    var second: Int = 0

    override fun toString(): String {
        return "DailyPrice(dailyOpen=$dailyOpen, dailyHigh=$dailyHigh, dailyLow=$dailyLow, dailyClose=$dailyClose, dailyVolume=$dailyVolume)"
    }

    fun setDate(value: String){
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        day = LocalDate.parse(value.split(" ").first())

        var array = value.split(" ").last().split(":").toTypedArray()
        hour = array[0].toInt()
        minute = array[1].toInt()
        second = array[2].toInt()
    }
}