package com.soumeswar.anonchat.data

data class Chat(
    val peer : Peer,
    val message : List<Message> = emptyList(),
    val unreadCount : Int = 0
)