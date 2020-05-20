package com.saashm.annoyingex

import android.app.Application
import android.widget.Toast
import com.saashm.annoyingex.managers.AnnoyUserManager
import com.saashm.annoyingex.managers.DataManager
import com.saashm.annoyingex.managers.TextNotificationManager

class AnnoyingExApp:Application() {
    lateinit var dataManager: DataManager
        private set
    lateinit var notificationManager: TextNotificationManager
        private set
    lateinit var annoyUserManager: AnnoyUserManager
        private set
    lateinit var currentText: String
    override fun onCreate() {
        super.onCreate()
        // Set Managers
        dataManager = DataManager(this)
        notificationManager = TextNotificationManager(this)
        annoyUserManager = AnnoyUserManager(this)
        currentText = getString(R.string.noTextDefault)
        // Get the text data
        dataManager.getTexts({list ->
            dataManager.allTexts = list.messages
            updateCurrText()
            Toast.makeText(this, "Got the texts!", Toast.LENGTH_LONG).show()
        }, {
            Toast.makeText(this, "Could not fetch text list", Toast.LENGTH_LONG).show()
        })
    }
    fun updateCurrText() {
        dataManager.allTexts?.let {
            currentText = it.random()
        }
    }
}