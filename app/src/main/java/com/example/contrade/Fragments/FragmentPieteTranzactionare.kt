package com.example.contrade.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.contrade.Api.Company
import com.example.contrade.Api.DailyPrice
import com.example.contrade.Api.JsonBuilder
import com.example.contrade.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class FragmentPieteTranzactionare : Fragment() {

    private val client = OkHttpClient()
    companion object {

        fun newInstance(): FragmentPieteTranzactionare {
            return FragmentPieteTranzactionare()
        }
    }
    //OkHttpClient creates connection pool between client and server


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_piete_tranzactionare, container, false)

        var textBox = view.findViewById<TextView>(R.id.markets_textView)

        var edtText = view.findViewById<EditText>(R.id.markets_edtText)
        var search = view.findViewById<ImageView>(R.id.markets_search)

        search.setOnClickListener {
            var companyName = edtText.text.toString()
            var url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+ companyName +"&interval=5min&apikey=demo"

            val request = Request.Builder()
                    .url(url)
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    var str_response = response.body!!.string()
                    var builder = JsonBuilder(str_response)
                    builder.build()
                    var company = builder.getResult()

                    activity?.runOnUiThread {
                        textBox.text = company.toString()
                    }
                }
            })
        }

        return view
    }

}