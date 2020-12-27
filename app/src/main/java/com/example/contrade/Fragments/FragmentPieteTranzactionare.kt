package com.example.contrade.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.contrade.Api.JsonBuilder
import com.example.contrade.BuyActivity
import com.example.contrade.MainActivity
import com.example.contrade.R
import com.example.contrade.SellActivity
import okhttp3.*
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
        (activity as MainActivity).toolbar.findViewById<TextView>(R.id.main_activity_toolbar_title).text = "Piete de tranzactionare"

        var textBox = view.findViewById<TextView>(R.id.markets_textView)

        var edtText = view.findViewById<EditText>(R.id.markets_edtText)
        var search = view.findViewById<ImageView>(R.id.markets_search)

        //card view elements
        var cardView = view.findViewById<CardView>(R.id.markets_card_view)
        var companySymbol = view.findViewById<TextView>(R.id.markets_company_name)
        var companyValue = view.findViewById<TextView>(R.id.markets_company_value)
        var buyButton = view.findViewById<Button>(R.id.markets_buy_button)
        var sellButton = view.findViewById<Button>(R.id.markets_sell_button)

        search.setOnClickListener {
            var companyName = edtText.text.toString()
            cardView.visibility = View.GONE
            var url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+companyName+"&interval=5min&apikey=6Z3D5ZVPMIWPLD6R"

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
                        if (company.companySymbol != "") {
                            cardView.visibility = View.VISIBLE
                            companySymbol.text = company.companySymbol
                            companyValue.text = company.calculateDifference(-1)
                            //textBox.text = company.toString()
                            buyButton.setOnClickListener {
                                val intent = Intent(activity, BuyActivity::class.java)
                                intent.putExtra(ExtraReply.REPLY_SYMBOL, company.companySymbol)
                                //return close price
                                intent.putExtra(ExtraReply.REPLY_CLOSE, company.calculateDifference(1))
                                //return open price
                                intent.putExtra(ExtraReply.REPLY_OPEN, company.calculateDifference(2))
                                //return percent price
                                intent.putExtra(ExtraReply.REPLY_PERCENT, company.calculateDifference(3))
                                startActivity(intent)
                            }
                            sellButton.setOnClickListener {
                                val intent = Intent(activity, SellActivity::class.java)
                                intent.putExtra(ExtraReply.REPLY_SYMBOL, company.companySymbol)
                                //return close price
                                intent.putExtra(ExtraReply.REPLY_CLOSE, company.calculateDifference(1))
                                //return open price
                                intent.putExtra(ExtraReply.REPLY_OPEN, company.calculateDifference(2))
                                //return percent price
                                intent.putExtra(ExtraReply.REPLY_PERCENT, company.calculateDifference(-1))
                                startActivity(intent)
                            }
                        }
                    }
                }
            })
        }

        return view
    }

}