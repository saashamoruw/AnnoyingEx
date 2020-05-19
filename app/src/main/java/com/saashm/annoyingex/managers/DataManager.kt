package com.saashm.annoyingex.managers

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.saashm.annoyingex.backend.Message
import com.saashm.annoyingex.backend.UpdateTextListener

class DataManager(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)
    private var updateTextListener: UpdateTextListener? = null
    var allTexts: List<String>? = null
    fun getTexts(onListReady: (Message) -> Unit, onError: (() -> Unit)? = null) {
        val emailURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val request = StringRequest(
            Request.Method.GET, emailURL,
            { response ->
                // Success
                val gson = Gson()
                val messages = gson.fromJson(response, Message::class.java)
                onListReady(messages)
                updateTexts()
            },
            {
                onError?.invoke()
            }
        )
        queue.add(request)
    }
    fun updateTexts() {
        updateTextListener?.updateTexts()
    }

}