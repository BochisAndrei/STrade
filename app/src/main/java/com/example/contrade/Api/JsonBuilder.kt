package com.example.contrade.Api

import android.util.Log
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import org.json.JSONObject
import java.io.StringReader

class JsonBuilder(var str_response: String) : Builder{
    var company : Company = Company()

    override fun build() {
        var jObject = JSONObject(str_response)

        if(jObject.has("Meta Data")){
            var aJsonMetaData = JSONObject(jObject.getString("Meta Data"))
            var symbol = aJsonMetaData.getString("2. Symbol")
            var lastRefreshed = aJsonMetaData.getString("3. Last Refreshed")
            var interval = aJsonMetaData.getString("4. Interval")
            var timeZone = aJsonMetaData.getString("6. Time Zone")
            company = Company(symbol,lastRefreshed,interval,timeZone)

            var aJsonTimeSeries = JSONObject(jObject.getString("Time Series (5min)"))
            var array = getDatesArray(str_response)
            array.forEach {
                var date = JSONObject(aJsonTimeSeries.getString(it))
                var open = date.getString("1. open")
                var high = date.getString("2. high")
                var low = date.getString("3. low")
                var close = date.getString("4. close")
                var volume = date.getString("5. volume")
                var price = DailyPrice(open.toDouble(), high.toDouble(), low.toDouble(), close.toDouble(), volume.toInt())
                price.setDate(it)
                company.companyStockPrices.add(price)
            }
        }

    }

    override fun getResult() : Company{
        return company
    }


    //function that gets a json string as a parameter and returns an array of dates from the json
    private fun getDatesArray(str_response : String) : Array<String>{
        val parsed = Klaxon().parseJsonObject(StringReader(str_response))
        val timeSeries = parsed.filter {
            it.key == "Time Series (5min)"
        }.map {
            it.value as JsonObject
        }
        var text: String = ""
        timeSeries.forEach {
            text += it.toString()
        }

        val strs = text.split(",").toTypedArray()
        return strs
    }

}