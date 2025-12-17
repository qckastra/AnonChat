package com.soumeswar.anonchat.tor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.torproject.jni.TorService

class TorStatusReceiver(private val onReady: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TorService.ACTION_STATUS && intent.getStringExtra(TorService.EXTRA_STATUS)  == TorService.STATUS_ON) {
            onReady()
        }
    }
}