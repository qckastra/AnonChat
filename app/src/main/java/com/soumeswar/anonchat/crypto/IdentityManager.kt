package com.soumeswar.anonchat.crypto

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PublicKey
import android.util.Base64
import java.security.PrivateKey

object IdentityManager {
    private const val ALGORITHM = "Ed25519"

    fun generateIdentity() : KeyPair {
        val gen = KeyPairGenerator.getInstance(ALGORITHM)
        return gen.generateKeyPair()
    }

    fun encodePublicKey(key : PublicKey) : String = Base64.encodeToString(key.encoded, Base64.NO_WRAP)
    fun encodePrivateKey(key : PrivateKey) : String = Base64.encodeToString(key.encoded, Base64.NO_WRAP)

    fun decodePublicKey(encoded : String) : PublicKey {
        val bytes = Base64.decode(encoded, Base64.NO_WRAP)
        return java.security.KeyFactory
            .getInstance(ALGORITHM)
            .generatePublic(java.security.spec.X509EncodedKeySpec(bytes))
    }

    fun decodePrivateKey(encoded : String) : PrivateKey {
        val bytes = Base64.decode(encoded, Base64.NO_WRAP)
        return java.security.KeyFactory
            .getInstance(ALGORITHM)
            .generatePrivate(java.security.spec.X509EncodedKeySpec(bytes))
    }
}