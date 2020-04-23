package com.vonage.client.envision.adapter.result

import com.vonage.client.envision.adapter.model.VonageApiError
import com.vonage.client.envision.adapter.model.VonageConversation

sealed class GetConversationResult {
    data class Success(val conversation: VonageConversation): GetConversationResult()
    data class Error(val apiError: VonageApiError): GetConversationResult()
}
