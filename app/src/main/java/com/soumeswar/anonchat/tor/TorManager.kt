package com.soumeswar.anonchat.tor

import android.content.Context
import android.content.Intent

class TorManager(private val context : Context) {
    fun startTor() {
        val intent = Intent(context, org.torproject.jni.TorService::class.java)
        context.startService(intent)
    }
    fun stopTor() {
        context.stopService(
            Intent(context, org.torproject.jni.TorService::class.java)
        )
    }
}