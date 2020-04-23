package com.vonage.client.envision.adapter.model

import com.nexmo.client.NexmoEventsPage

class VonageEventsPage(private val nexmoEventsPage: NexmoEventsPage) {
    val data = if (nexmoEventsPage.data.isNullOrEmpty()) listOf() else nexmoEventsPage.data.toList()
}