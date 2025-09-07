package com.example.lab3

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private val prefsName = "Lab3Prefs"
    private val nameKey = "user_name"
    private val nameActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        val buttonNext: Button = findViewById(R.id.buttonNext)

        sharedPreferences = getSharedPreferences(prefsName, MODE_PRIVATE)

        // Load saved name
        val savedName = sharedPreferences.getString(nameKey, "")
        if (!savedName.isNullOrEmpty()) {
            editTextName.setText(savedName)
        }

        buttonNext.setOnClickListener {
            val name = editTextName.text.toString()
            val intent = Intent(this, NameActivity::class.java)
            intent.putExtra("name", name)
            startActivityForResult(intent, nameActivityRequestCode)
        }
    }

    override fun onPause() {
        super.onPause()
        // Save name to SharedPreferences
        val name = editTextName.text.toString()
        sharedPreferences.edit {
            putString(nameKey, name)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == nameActivityRequestCode) {
            if (resultCode == RESULT_OK && data != null) {
                val response = data.getIntExtra("response", -1)
                if (response == 1) {
                    // User is happy, close the app
                    finish()
                }
                // If response is 0, do nothing (user wants to change name)
            }
        }
    }
}