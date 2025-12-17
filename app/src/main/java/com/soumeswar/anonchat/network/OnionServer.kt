package com.soumeswar.anonchat.network

import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

class OnionServer(
    private val port : Int,
    private val onMessage : (String, Socket) -> Unit
) {
    private var running = false

    fun start() {
        running = true
        thread(name = "OnionServer") {
            val server = ServerSocket(port)
            while (running) {
                val socket = server.accept()
                handleConnection(socket)
            }
        }
    }

private fun handleConnection(socket : Socket) {
        thread {
            val reader = socket.getInputStream().bufferedReader()
            val writer = socket.getOutputStream().bufferedWriter()

            val hello = reader.readLine()

            if (hello != "HELLO") {
                socket.close()
                return@thread
            }

            writer.write("OK\n")

            while (true) {
                val line = reader.readLine() ?: break
                onMessage(line, socket)
            }
            socket.close()
        }
    }
    fun stop() {
        running = false
    }
}