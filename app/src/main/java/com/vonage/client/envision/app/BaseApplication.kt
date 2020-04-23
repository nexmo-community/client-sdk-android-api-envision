package com.vonage.client.envision.app

import android.app.Application
import com.vonage.client.envision.adapter.VonageClient
import com.vonage.client.envision.adapter.VonageClientLogLevel
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Init timber
        Timber.plant(Timber.DebugTree())

        // Init the VonageClient. You can retrieve VonageClient instance latter by using VonageClient.instance
        VonageClient.init(
            context = this,
            logLevel = VonageClientLogLevel.SENSITIVE,
            environmentHost = "https://ws.nexmo.com"
        )
    }
}