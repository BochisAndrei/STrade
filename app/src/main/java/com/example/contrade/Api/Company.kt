package com.example.contrade.Api

import android.util.Log
import kotlin.math.round

class Company(
    var companySymbol: String = "",
    var companyLastRefreshed : String = "",
    var companyInterval : String = "",
    var companyTimeZone : String = ""

) {
    var companyStockPrices: ArrayList<DailyPrice> = ArrayList()

    override fun toString(): String {
        return "Company(companySymbol='$companySymbol', companyLastRefreshed='$companyLastRefreshed', companyInterval='$companyInterval', companyTimeZone='$companyTimeZone', companyStockPrices=$companyStockPrices)"
    }

    fun calculateDifference(operation : Int) : String {
        //if operation is -1 return the last return else if 1 return open price if 2 return close price
        var hourMin = companyStockPrices.first().hour
        var minuteMin = 59
        var hourMax = -1
        var minuteMax = -1
        var open = 0.0
        var close = 0.0
        companyStockPrices.forEach {
            if(hourMin > it.hour) hourMin = it.hour
            if(hourMax < it.hour) hourMax = it.hour
        }
        companyStockPrices.forEach {
            if(it.hour == hourMin)
            if(minuteMin > it.minute) minuteMin = it.minute

            if(it.hour == hourMax)
            if(minuteMax < it.minute) minuteMax = it.minute
        }
        companyStockPrices.forEach {
            if(it.hour == hourMin && it.minute == minuteMin){
                open = it.dailyOpen
            }
            if(it.hour == hourMax && it.minute == minuteMax){
                close = it.dailyClose
            }
        }
        var result = close-open

        var percent = result/open * 100

        //return close price
        if(operation == 1){
            return close.toString()
        }
        //return open price
        if(operation == 2){
            return open.toString()
        }

        return String.format("%.2f", result) + " (" + String.format("%.2f", percent) + "%)"
    }
}