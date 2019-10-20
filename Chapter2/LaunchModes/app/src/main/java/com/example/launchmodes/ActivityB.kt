package com.example.launchmodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.letters.*

class ActivityB : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        val buttonClickListener = View.OnClickListener { view ->

            when (view.id) {
                R.id.letterA -> startActivity(Intent(this, ActivityA::class.java))
                R.id.letterB -> startActivity(Intent(this, ActivityB::class.java))
                R.id.letterC -> startActivity(Intent(this, ActivityC::class.java))
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.unexpected_button_pressed),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

        letterA.setOnClickListener(buttonClickListener)
        letterB.setOnClickListener(buttonClickListener)
        letterC.setOnClickListener(buttonClickListener)
    }
}
