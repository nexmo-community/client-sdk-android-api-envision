package com.vonage.client.envision.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vonage.client.envision.R
import com.vonage.client.envision.adapter.VonageClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import timber.log.Timber

class LoadConversattionEventsFlow : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = VonageClient.instance

        lifecycleScope.launch {

        val pageData = client.getConversationFlow("CONVERSATION_ID")
                .catch { Timber.d("Unable to load conversation ${it.message}") }
                .flatMapConcat { it.getEventsFlow(100) }
                .catch { Timber.d("Unable to load conversation events ${it.message}") }
                .onEach { it.data }
                .single()
        }
    }
}
