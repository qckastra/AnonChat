package com.soumeswar.anonchat.data

sealed class ChatState {

    object Loading : ChatState()

    object Empty : ChatState()

    data class Active(
        val chat: Chat
    ) : ChatState()

    data class Error(
        val reason: String
    ) : ChatState()
}
