package com.example.contrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.contrade.Fragments.ExtraReply

class SellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)

        var textSymbol = findViewById<TextView>(R.id.activity_sell_symbol)
        var textClose = findViewById<TextView>(R.id.activity_sell_close_price)
        var textPercent = findViewById<TextView>(R.id.activity_sell_percent)
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

        var edtText = findViewById<EditText>(R.id.activity_sell_edt_text)
        edtText.setText("5000")
        var buttonMinus = findViewById<ImageButton>(R.id.activity_sell_button_minus)
        var buttonPlus = findViewById<ImageButton>(R.id.activity_sell_button_plus)

        buttonMinus.setOnClickListener {
            var value = Integer.parseInt(edtText.text.toString()) - 500
            if(value > 0) edtText.setText(value.toString())
        }
        buttonPlus.setOnClickListener {
            var value = Integer.parseInt(edtText.text.toString()) + 500
            edtText.setText(value.toString())
        }

        var backButton = findViewById<ImageView>(R.id.activity_sell_back)
        backButton.setOnClickListener {
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            finish()
        }

        var sellButton = findViewById<Button>(R.id.activity_sell_button_sell)
        sellButton.isClickable = false

        var buyButton = findViewById<Button>(R.id.activity_sell_button_buy)
        buyButton.setOnClickListener {
            sellButton.isClickable = true
            val intent = Intent(this, BuyActivity::class.java)
            intent.putExtra(ExtraReply.REPLY_SYMBOL, symbol)
            //return close price
            intent.putExtra(ExtraReply.REPLY_CLOSE, close)
            //return open price
            intent.putExtra(ExtraReply.REPLY_OPEN, "")
            //return percent price
            intent.putExtra(ExtraReply.REPLY_PERCENT, percent)
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        }
    }
}