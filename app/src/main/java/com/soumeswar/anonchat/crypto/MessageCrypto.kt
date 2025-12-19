package com.soumeswar.anonchat.crypto

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
object MessageCrypto {
    private const val TRANSFORMATION = "AES/GCM/NoPadding"
    private const val TAG_BITS = 128

    fun encrypt(plain : String, key : ByteArray) : String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = ByteArray(12)
        SecureRandom().nextBytes(iv)

        cipher.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(key, "AES"),
            GCMParameterSpec(TAG_BITS, iv)
        )

        val encrypted = cipher.doFinal(plain.toByteArray())

        val combined = iv + encrypted

        return Base64.encodeToString(combined, Base64.NO_WRAP)
    }

    fun decrypt(cipherText : String, key : ByteArray) : String {
        val data = Base64.decode(cipherText, Base64.NO_WRAP)
        val iv = data.copyOfRange(0, 12)
        val encrypted = data.copyOfRange(12, data.size)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init (
            Cipher.DECRYPT_MODE,
            SecretKeySpec(key, "AES"),
            GCMParameterSpec(TAG_BITS, iv),
            )

        return String(cipher.doFinal(encrypted))
    }
}