package com.vonage.client.envision.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vonage.client.envision.R
import com.vonage.client.envision.adapter.VonageClient
import com.vonage.client.envision.adapter.model.VonageConversation
import com.vonage.client.envision.adapter.result.GetConversationEventsResult
import com.vonage.client.envision.adapter.result.GetConversationResult

import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = VonageClient.instance

        lifecycleScope.launch {
            when (val conversationResult = client.getConversation("CONVERSATION_ID")) {
                is GetConversationResult.Success -> getConversationEvents(conversationResult.conversation)
                is GetConversationResult.Error -> Timber.d("Unable to load conversation ${conversationResult.apiError.message}")
            }
        }
    }

    private suspend fun getConversationEvents(conversation: VonageConversation) {
        when (val eventsResult = conversation.getEvents(100)) {
            is GetConversationEventsResult.Success -> {
                eventsResult.page.data
            }
            is GetConversationEventsResult.Error -> {
                Timber.d("Unable to load conversation events ${eventsResult.apiError.message}")
            }
        }
    }
}
