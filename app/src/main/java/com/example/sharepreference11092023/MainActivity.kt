package com.example.sharepreference11092023

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var ckBoxRemember: CheckBox
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.button_login)
        edtEmail = findViewById(R.id.edit_text_email)
        edtPassword = findViewById(R.id.edit_text_password)
        ckBoxRemember = findViewById(R.id.checkbox_remember)

        // Khai bao
        sharedPreferences = getSharedPreferences("app-cache", Context.MODE_PRIVATE)
        val isChecked = sharedPreferences.getBoolean("isChecked", false)
        if (isChecked) {
            val email = sharedPreferences.getString("email", "")
            val password = sharedPreferences.getString("password", "")

            edtEmail.setText(email)
            edtPassword.setText(password)
            ckBoxRemember.isChecked = true
        }

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (email == "phat@gmail.com" && password == "88888888") {
                sharedPreferences.edit {
                    if (ckBoxRemember.isChecked) {
                        putString("email", email)
                        putString("password", password)
                        putBoolean("isChecked", true)
                    } else {
                        clear()
                    }
                }
            } else {
                Toast.makeText(this, "Input invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}