package com.vonage.client.envision.adapter.model

import androidx.annotation.IntRange
import com.nexmo.client.NexmoConversation
import com.nexmo.client.NexmoEventsPage
import com.nexmo.client.NexmoPageOrder
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.vonage.client.envision.adapter.result.GetConversationEventsResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class VonageConversation(private val conversation: NexmoConversation) {

    suspend fun getEvents(
        @IntRange(from = 1L, to = 100L) pageSize: Int,
        order: NexmoPageOrder = NexmoPageOrder.NexmoMPageOrderAsc,
        eventType: String? = null
    ): GetConversationEventsResult = suspendCoroutine { continuation ->
        conversation.getEvents(pageSize, order, eventType, object : NexmoRequestListener<NexmoEventsPage> {
            override fun onSuccess(nexmoEventsPage: NexmoEventsPage?) {
                continuation.resume(GetConversationEventsResult.Success(VonageEventsPage(nexmoEventsPage!!)))
            }

            override fun onError(apiError: NexmoApiError) {
                continuation.resume(GetConversationEventsResult.Error(VonageApiError(apiError)))
            }
        })
    }
}