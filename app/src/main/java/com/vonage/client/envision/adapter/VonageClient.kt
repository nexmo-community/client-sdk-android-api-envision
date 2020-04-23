package com.vonage.client.envision.adapter

import android.content.Context
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoConversation
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.utils.logger.ILogger
import com.vonage.client.envision.adapter.model.VonageApiError
import com.vonage.client.envision.adapter.model.VonageConversation
import com.vonage.client.envision.adapter.result.GetConversationResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

typealias VonageClientLogLevel = ILogger.eLogLevel

object VonageClient {

    val instance: VonageClient
    get() {
        verifyInitialized()
        return this
    }

    private var nexmoClient: NexmoClient? = null

    private var initialized = false
    fun init(
        context: Context,
        restEnvironmentHost: String = "https://api.nexmo.com/",
        environmentHost: String = "https://ws.nexmo.com",
        imageProcessingServiceUrl: String = "v1/image/",
        endpointPath: String = "/rtc/",
        logLevel: VonageClientLogLevel = VonageClientLogLevel.NONE,
        logKey: Long = 0L,
        iceServerUrls: List<String> = listOf("stun:stun.l.google.com`:19302"), // ToDo: This could be also a single URL?, so not always necessary to pas a list?
        useFirstIceCandidate: Boolean = false
    ): VonageClient {
        nexmoClient = NexmoClient.Builder()
            .restEnvironmentHost(restEnvironmentHost)
            .environmentHost(environmentHost)
            .imageProcessingServiceUrl(imageProcessingServiceUrl)
            .endpointPath(endpointPath)
            .logLevel(logLevel)
            .logKey(logKey)
            .iceServerUrls(iceServerUrls.toTypedArray())
            .useFirstIceCandidate(useFirstIceCandidate)
            .build(context)

        initialized = true
        return this
    }

    private fun verifyInitialized() {
        check(initialized) { "VonageClient is not initialized. Use VonageClient.init()" }
    }

    suspend fun getConversation(conversationId: String): GetConversationResult {
        verifyInitialized()

        return suspendCoroutine { continuation ->
            nexmoClient?.getConversation(conversationId, object : NexmoRequestListener<NexmoConversation> {

                override fun onSuccess(conversation: NexmoConversation?) {
                    continuation.resume(GetConversationResult.Success(VonageConversation(conversation!!)))
                }

                override fun onError(apiError: NexmoApiError) {
                    continuation.resume(GetConversationResult.Error(VonageApiError(apiError)))
                }
            })
        }
    }

    @ExperimentalCoroutinesApi
    fun getConversationFlow(conversationId: String): Flow<VonageConversation> {
        verifyInitialized()

        return callbackFlow {
            nexmoClient?.getConversation(conversationId, object : NexmoRequestListener<NexmoConversation> {

                override fun onSuccess(conversation: NexmoConversation?) {
                    offer(VonageConversation(conversation!!))
                }

                override fun onError(apiError: NexmoApiError) {
                    val clientException = VonageClientException(VonageApiError(apiError))
                    close(clientException)
                }
            })
        }
    }
}
