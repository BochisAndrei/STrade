package com.example.contrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)

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
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        }
    }
}