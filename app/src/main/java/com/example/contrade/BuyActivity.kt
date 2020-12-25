package com.example.contrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class BuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

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
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
    }
}