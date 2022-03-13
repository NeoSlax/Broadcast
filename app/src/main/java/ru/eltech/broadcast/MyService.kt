package ru.eltech.broadcast

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.concurrent.thread

class MyService : Service() {

    private val localBroadcastManager: LocalBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            for (i in 0..10) {
                Intent("loadingAction").apply {
                    putExtra("loaded", i * 10)
                    localBroadcastManager.sendBroadcast(this)
                }
                Thread.sleep(1000L)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}