package com.soumeswar.anonchat.data

data class Message(
    val from : String,
    val body : String,
    val timestamp : Long = System.currentTimeMillis(),
    val outgoing : Boolean
)