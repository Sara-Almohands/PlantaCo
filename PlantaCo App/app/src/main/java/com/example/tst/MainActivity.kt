package com.example.tst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setting initial data for number pickers
        nptemp.minValue = 0
        nptemp.maxValue = 70
        nptemp.value = 25

        nphum.minValue = 0
        nphum.maxValue = 100
        nphum.value = 25


        //imageView.setImageResource(R.drawable.dottttt)
        btnS.setOnClickListener {
            //Setting the On Click Listener for the button
            val intent = Intent(this, Items::class.java)
            intent.putExtra("tt", nptemp.value) // passing the temperature value
            intent.putExtra("hh", nphum.value) // passing the humidity value
            startActivity(intent) // starting the new window
        }







    }

}
