package com.example.tst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_plant.*


class Plant : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)
        //receiving and reading data from the prev window
        val name = intent.getStringExtra("name")
        val info = intent.getStringExtra("info")
        val image = intent.getIntExtra("image",R.drawable.ic_launcher_background)
        tvname.text = name
        tvinfo.text = info
        imagemain.setImageResource(image)


    }
}
