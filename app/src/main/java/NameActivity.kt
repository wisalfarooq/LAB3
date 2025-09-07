package com.example.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        val textViewWelcome: TextView = findViewById(R.id.textViewWelcome)
        val buttonThankYou: Button = findViewById(R.id.buttonThankYou)
        val buttonDontCall: Button = findViewById(R.id.buttonDontCall)

        // Get the name from intent
        val name = intent.getStringExtra("name")
        if (name != null) {
            textViewWelcome.text = getString(R.string.welcome, name)
        }

        buttonThankYou.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("response", 1)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        buttonDontCall.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("response", 0)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}