package com.ibtikar.espresso

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var greetingView: TextView
    private lateinit var buttonGreet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = ItemsAdapter(listOf("one", "two", "three", "four"))
        greetingView = findViewById(R.id.greetingView)
        buttonGreet = findViewById(R.id.greet_button)

        buttonGreet.setOnClickListener({
            greet()
        })

    }

    private fun greet() {
        buttonGreet.isEnabled = false
        greetingView.setText(R.string.helloWorld)
    }
}
