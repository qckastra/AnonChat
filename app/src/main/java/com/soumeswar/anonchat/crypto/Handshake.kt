package com.soumeswar.anonchat.crypto

import android.util.Base64
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyAgreement

object Handshake {
    private val ALGORITHM = "X25519"

    fun generateEphemealKeypair() : KeyPair = KeyPairGenerator.getInstance(ALGORITHM).generateKeyPair()

    fun deriveSharedSecret(
        privateKey : PrivateKey,
        peerPublicKey : PublicKey
    ) : ByteArray {
        val keyAgreement = KeyAgreement.getInstance(ALGORITHM)
        keyAgreement.init(privateKey)
        keyAgreement.doPhase(peerPublicKey, true)
        val shared = keyAgreement.generateSecret()

        return MessageDigest
            .getInstance("SHA-256")
            .digest(shared)
    }

    fun encodePublicKey(key : PublicKey) : String = Base64.encodeToString(key.encoded, Base64.NO_WRAP)

    fun decodePublicKey(encoded : String) : PublicKey {
        val bytes = Base64.decode(encoded, Base64.NO_WRAP)
        return KeyFactory
            .getInstance(ALGORITHM)
            .generatePublic(X509EncodedKeySpec(bytes))
    }

}