package com.soumeswar.anonchat.data

data class Peer(
    val onionAddress : String,
    val nickname : String? = null,
    val isOnline : Boolean = false,
    val lastSeen : Long? = null,
    val publicKey : String? = null
)