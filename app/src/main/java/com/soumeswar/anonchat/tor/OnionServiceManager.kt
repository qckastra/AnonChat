package com.soumeswar.anonchat.tor

import com.soumeswar.anonchat.data.OnionServiceInfo
import net.freehaven.tor.control.TorControlCommands
import net.freehaven.tor.control.TorControlConnection

class OnionServiceManager (private val torControl : TorControlConnection){

    init {
        torControl.authenticate(byteArrayOf())
    }

    fun createChatService(localPort : Int, savedPrivateKey : String? = null) : OnionServiceInfo {


        val ports = mapOf(
            80 to "127.0.0.1:$localPort"
        )

        val keySpec = savedPrivateKey?.let {
            "ED25519-V3:$it"
        } ?: "NEW:BEST"

        val result = torControl.addOnion(
            keySpec,
            ports,
            false,
            false
        )

        val onionAddress = result[TorControlCommands.HS_ADDRESS] + ".onion"

        val privateKey = result[TorControlCommands.HS_PRIVKEY]

        return OnionServiceInfo(
            onionAddress = onionAddress,
            privateKey = privateKey
        )
    }

    fun removeChatService(onionAddress : String)
    {
        val serviceId = onionAddress.removeSuffix(".onion")
        torControl.delOnion(serviceId)
    }
}