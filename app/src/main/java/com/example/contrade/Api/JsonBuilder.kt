package com.example.contrade.Api

import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.StringReader

class JsonBuilder(var url: String, var activity: FragmentActivity?, var textBox: TextView) : Builder{
    lateinit var company: Company
    private val client = OkHttpClient()

    override fun build() {
        run(url, textBox, activity)
    }

    override fun getResult() : Company{

        return company
    }
    private fun run(url: String, textBox : TextView, activity: FragmentActivity?) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body!!.string()
                var jObject = JSONObject(str_response)

                var aJsonMetaData = JSONObject(jObject.getString("Meta Data"))
                var symbol = aJsonMetaData.getString("2. Symbol")
                var lastRefreshed = aJsonMetaData.getString("3. Last Refreshed")
                var interval = aJsonMetaData.getString("4. Interval")
                var timeZone = aJsonMetaData.getString("6. Time Zone")
                company = Company(symbol,lastRefreshed,interval,timeZone)

                var aJsonTimeSeries = JSONObject(jObject.getString("Time Series (5min)"))
                var array = getDatesArray(str_response)
                var text = ""
                array.forEach {
                    var date = JSONObject(aJsonTimeSeries.getString(it))
                    var open = date.getString("1. open")
                    var high = date.getString("2. high")
                    var low = date.getString("3. low")
                    var close = date.getString("4. close")
                    var volume = date.getString("5. volume")
                    var price = DailyPrice(open.toDouble(), high.toDouble(), low.toDouble(), close.toDouble(), volume.toInt())
                    company.companyStockPrices.add(price)
                }

                activity?.runOnUiThread {
                        textBox.text = company.toString()
                }
            }
        })
    }

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