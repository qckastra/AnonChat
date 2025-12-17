package com.soumeswar.anonchat.network

import java.net.InetSocketAddress
import java.net.Proxy
import java.net.Socket

class OnionClient(
    private val SOCKS_HOST : String,
    private val SOCKS_PORT : Int = 9050
) {
    fun connect(onion : String, port : Int = 80) : Socket {
        val proxy = Proxy(
            Proxy.Type.SOCKS,
            InetSocketAddress(SOCKS_HOST, SOCKS_PORT)
        )
        val socket = Socket(proxy)
        socket.connect(
            InetSocketAddress(onion, port),
            60_000
        )
        performHandshake(socket)
        return socket
    }

    private fun performHandshake(socket : Socket) {
        val reader = socket.getInputStream().bufferedReader()
        val writer = socket.getOutputStream().bufferedWriter()

        writer.write("HELLO")
        writer.flush()

        val response = reader.readLine()

        if (response != "OK") {
            socket.close()
            throw IllegalStateException("Handshake Failed")
        }
    }
}