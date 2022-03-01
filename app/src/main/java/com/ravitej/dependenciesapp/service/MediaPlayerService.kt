package com.ravitej.dependenciesapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ravitej.dependenciesapp.MainActivity
import com.ravitej.dependenciesapp.R

const val NOTIFICATION_ACTION_PLAY = "action_play"
const val NOTIFICATION_ACTION_STOP = "action_stop"
const val AUDIO_FILE = "happy_day.mp3"

class MediaPlayerService : Service() {

    private lateinit var player: MediaPlayer

    companion object {
        const val LOG_TAG = "MediaPlayerService"
    }

    /**
     * System invokes this method by calling startService() when another component(such as activity) requests the
     * service be started.
     * Note: If you only want to provide binding, you don't need to implement this method.
     *
     * If system kills this service, it restarts it as soon as the resources are available but this also depends on
     * the value returned from this method.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(LOG_TAG, "onStartCommand")
        startMusic()
        when (intent?.action) {
            NOTIFICATION_ACTION_PLAY -> startMusic()
            NOTIFICATION_ACTION_STOP -> stopMusic()
        }
        return START_STICKY
    }

    /**
     * System invokes this method by calling bindService(), when another components wants to bind with this Service.
     * In your Service implementation, we must provide an interface for clients to interact with the service by
     * returning IBinder.
     *
     * If you don't want to provide binding then you should return "null"
     */
    override fun onBind(intent: Intent?): IBinder? {
        Log.e(LOG_TAG, "onBind")
        return null
    }

    /**
     * The system calls this method for the first time to perform one-time setup operations when the service is
     * initially created.
     */
    override fun onCreate() {
        super.onCreate()
        Log.e(LOG_TAG, "onCreate")
    }

    /**
     * The system invokes this method when the service is destroyed/no longer in use. You should clean up any
     * resources such as threads, registered listeners or receivers.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG_TAG, "onDestroy")
    }

    private fun startMusic() {
        try {
            player.stop()
            player.release()
        } catch (e: UninitializedPropertyAccessException) {
        }

        player = MediaPlayer().also {
            assets.openFd(AUDIO_FILE).use { asset ->
                it.setDataSource(asset.fileDescriptor, asset.startOffset, asset.length)
            }
            it.prepare()
            it.start()
        }

        displayNotification()
    }

    private fun stopMusic() {
        try {
            player.stop()
        } catch (e: UninitializedPropertyAccessException) {
        }
        stopForeground(true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = "my_service"
        val channelName = "Music service"

        NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE).also {
            it.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(it)
        }

        return channelId
    }

    private fun displayNotification() {
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else {
            ""
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val playIntent = getPendingIntent(NOTIFICATION_ACTION_PLAY)
        val stopIntent = getPendingIntent(NOTIFICATION_ACTION_STOP)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Playing music")
            .setContentText(AUDIO_FILE)
            .setSmallIcon(R.drawable.ic_run)
            .setContentIntent(pendingIntent)
            .addAction(0, "Play", playIntent)
            .addAction(0, "Stop", stopIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setWhen(0)
            .build()
        startForeground(1001, notification)
    }

    private fun getPendingIntent(action: String): PendingIntent {
        var serviceIntent = Intent(this, MediaPlayerService::class.java).also {
            it.action = action
        }
        return PendingIntent.getService(this, 0, serviceIntent, 0)
    }
}
