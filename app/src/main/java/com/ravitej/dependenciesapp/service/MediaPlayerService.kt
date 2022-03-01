package com.ravitej.dependenciesapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MediaPlayerService : Service() {

    /**
     * System invokes this method by calling startService() when another component(such as activity) requests the
     * service be started.
     * Note: If you only want to provide binding, you don't need to implement this method.
     *
     * If system kills this service, it restarts it as soon as the resources are available but this also depends on
     * the value returned from this method.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * System invokes this method by calling bindService(), when another components wants to bind with this Service.
     * In your Service implementation, we must provide an interface for clients to interact with the service by
     * returning IBinder.
     *
     * If you don't want to provide binding then you should return "null"
     */
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    /**
     * The system calls this method for the first time to perform one-time setup operations when the service is
     * initially created.
     */
    override fun onCreate() {
        super.onCreate()
    }

    /**
     * The system invokes this method when the service is destroyed/no longer in use. You should clean up any
     * resources such as threads, registered listeners or receivers.
     */
    override fun onDestroy() {
        super.onDestroy()
    }
}
