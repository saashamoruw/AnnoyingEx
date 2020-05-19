package com.saashm.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.saashm.annoyingex.backend.UpdateTextListener
import com.saashm.annoyingex.managers.DataManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var dataManager: DataManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAgain.setOnClickListener() {

        }
        btnBlock.setOnClickListener() {

        }
    }

}
