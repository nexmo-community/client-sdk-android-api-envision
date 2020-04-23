package com.vonage.client.envision.adapter.result

import com.vonage.client.envision.adapter.model.VonageApiError
import com.vonage.client.envision.adapter.model.VonageEventsPage

sealed class GetConversationEventsResult {
    data class Success(val page: VonageEventsPage): GetConversationEventsResult()
    data class Error(val apiError: VonageApiError): GetConversationEventsResult()
}
