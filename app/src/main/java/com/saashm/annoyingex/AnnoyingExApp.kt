package com.saashm.annoyingex

import android.app.Application
import android.widget.Toast
import com.saashm.annoyingex.managers.DataManager

class AnnoyingExApp:Application() {
    lateinit var dataManager: DataManager
        private set
    var allTexts: List<String>? = null
    override fun onCreate() {
        super.onCreate()
        dataManager = DataManager(this)
        dataManager.getTexts({list ->
            dataManager.allTexts = list.messages
            dataManager.updateTexts()
            Toast.makeText(this, "Got the texts!", Toast.LENGTH_LONG).show()
        }, {
            Toast.makeText(this, "Could not fetch text list", Toast.LENGTH_LONG).show()
        })
    }


}