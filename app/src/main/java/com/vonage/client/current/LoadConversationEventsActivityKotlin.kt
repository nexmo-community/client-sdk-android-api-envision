package com.vonage.client.current

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoConversation
import com.nexmo.client.NexmoEventsPage
import com.nexmo.client.NexmoPageOrder
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import timber.log.Timber

class LoadConversationEventsActivityKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // No need for client initialization here. Client initialization is already done in BaseApplication class.
        // NexmoClient.Builder().build(this)
        val client = NexmoClient.get()
        client.login("JWT token")


        client.getConversation("CONVERSATION_ID", object : NexmoRequestListener<NexmoConversation> {
            override fun onSuccess(conversation: NexmoConversation?) {
                Timber.d("Conversation loaded")
                conversation?.getEvents(100, NexmoPageOrder.NexmoMPageOrderAsc, null,
                    object : NexmoRequestListener<NexmoEventsPage> {
                        override fun onSuccess(nexmoEventsPage: NexmoEventsPage?) {
                            nexmoEventsPage?.pageResponse?.data
                        }

                        override fun onError(apiError: NexmoApiError) {
                            Timber.d("Error: Unable to load conversation events ${apiError.message}")
                        }
                    })
            }

            override fun onError(apiError: NexmoApiError) {
                Timber.d("Error: Unable to load conversation ${apiError.message}")
            }
        })

    }
}
