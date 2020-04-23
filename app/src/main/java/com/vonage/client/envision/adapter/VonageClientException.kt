package com.vonage.client.envision.adapter

import com.vonage.client.envision.adapter.model.VonageApiError

class VonageClientException(val apiError: VonageApiError): RuntimeException() {
}