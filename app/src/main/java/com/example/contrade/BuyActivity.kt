package com.example.contrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.contrade.Fragments.ExtraReply

class BuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        var textSymbol = findViewById<TextView>(R.id.activity_buy_symbol)
        var textClose = findViewById<TextView>(R.id.activity_buy_close_price)
        var textPercent = findViewById<TextView>(R.id.activity_buy_percent)
        val intentFrom = intent
        var symbol : String = ""
        var close : String = ""
        var percent : String = ""

        if(intentFrom.hasExtra(ExtraReply.REPLY_SYMBOL)){
            symbol= intentFrom.getStringExtra(ExtraReply.REPLY_SYMBOL).toString()
            close = intentFrom.getStringExtra(ExtraReply.REPLY_CLOSE).toString()
            percent = intentFrom.getStringExtra(ExtraReply.REPLY_PERCENT).toString()
        }
        textSymbol.text = symbol
        textClose.text = close
        textPercent.text = percent

        var edtText = findViewById<EditText>(R.id.activity_buy_edt_text)
        var buttonMinus = findViewById<ImageButton>(R.id.activity_buy_button_minus)
        var buttonPlus = findViewById<ImageButton>(R.id.activity_buy_button_plus)

        buttonMinus.setOnClickListener {
            var value = Integer.parseInt(edtText.text.toString()) - 500
            if(value > 0) edtText.setText(value.toString())
        }
        buttonPlus.setOnClickListener {
            var value = Integer.parseInt(edtText.text.toString()) + 500
            if(value > 0)
            edtText.setText(value.toString())
        }

        var backButton = findViewById<ImageView>(R.id.activity_buy_back)
        backButton.setOnClickListener {
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            finish()
        }
        var buyButton = findViewById<Button>(R.id.activity_buy_button_buy)
        buyButton.isClickable = false
        var sellButton = findViewById<Button>(R.id.activity_buy_button_sell)
        sellButton.setOnClickListener {
            buyButton.isClickable = true
            val intent = Intent(this, SellActivity::class.java)
            intent.putExtra(ExtraReply.REPLY_SYMBOL, symbol)
            //return close price
            intent.putExtra(ExtraReply.REPLY_CLOSE, close)
            //return open price
            intent.putExtra(ExtraReply.REPLY_OPEN, "")
            //return percent price
            intent.putExtra(ExtraReply.REPLY_PERCENT, percent)
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
    }
}