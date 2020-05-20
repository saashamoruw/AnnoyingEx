package com.saashm.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.saashm.annoyingex.managers.AnnoyUserManager
import com.saashm.annoyingex.managers.TextNotificationManager.Companion.TEXT_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var annoyUserManager: AnnoyUserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Get managers
        annoyUserManager = (applicationContext as AnnoyingExApp).annoyUserManager
        // Set on click references
        btnAgain.setOnClickListener {
            (applicationContext as AnnoyingExApp).updateCurrText()
            annoyUserManager.startAnnoyingUser()

        }
        btnBlock.setOnClickListener {
            annoyUserManager.stopWork()
        }
        // If the user had clicked on notif then display that
        val textContent = intent.getStringExtra(TEXT_ID)
        if(textContent != null) {
            tvTextMsg.visibility = VISIBLE
            tvTextMsg.text = textContent
        } else {
            tvTextMsg.visibility = GONE
        }

    }
}
