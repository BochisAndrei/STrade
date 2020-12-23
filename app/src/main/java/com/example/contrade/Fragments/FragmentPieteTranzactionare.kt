package com.example.contrade.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.contrade.Api.JsonBuilder
import com.example.contrade.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class FragmentPieteTranzactionare : Fragment() {
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
        var companyName = "IBM"

        var url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+ companyName +"&interval=5min&apikey=demo"

        var builder = JsonBuilder(url, activity, textBox)
        builder.build()

        return view
    }


}