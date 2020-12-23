package com.example.contrade.Api

class Company(
    var companySymbol: String,
    var companyLastRefreshed : String,
    var companyInterval : String,
    var companyTimeZone : String

) {
    var companyStockPrices: ArrayList<DailyPrice> = ArrayList()

    override fun toString(): String {
        return "Company(companySymbol='$companySymbol', companyLastRefreshed='$companyLastRefreshed', companyInterval='$companyInterval', companyTimeZone='$companyTimeZone', companyStockPrices=$companyStockPrices)"
    }
}