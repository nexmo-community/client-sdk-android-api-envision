package com.vonage.client.envision.adapter.model

import com.nexmo.client.request_listener.NexmoApiError

class VonageApiError(apiError: NexmoApiError) {
    val message: String = apiError.message
}