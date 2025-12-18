package com.soumeswar.anonchat.data

data class ChatsOverviewState(
    val chats: List<Chat> = emptyList(),
    val isTorReady: Boolean = false
)

