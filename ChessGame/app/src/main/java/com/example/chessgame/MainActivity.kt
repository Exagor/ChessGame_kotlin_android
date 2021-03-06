package com.example.chessgame
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var NamePlayerWhite: EditText
    lateinit var NamePlayerBlack: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         NamePlayerWhite = findViewById<EditText>(R.id.editTextPersonName1)
         NamePlayerBlack = findViewById<EditText>(R.id.editTextPersonName2)
        button = findViewById(R.id.button)
        button.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        openMainActivity()

    }

    private fun openMainActivity() {
        val intent = Intent(this, EchecActivity::class.java).apply{}
        intent.putExtra("joueur blanc", NamePlayerWhite.text.toString())
        intent.putExtra("joueur noir", NamePlayerBlack.text.toString())
        startActivity(intent)

    }
}
