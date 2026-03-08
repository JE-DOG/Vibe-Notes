package ru.khinkal.springDemo.common.hash

import java.security.MessageDigest

object HashUtil {

    fun hash(value: String): String {
        val messageDigest = getMessageDigest()
        val hashBytes: ByteArray = messageDigest.digest(value.toByteArray())
        val hexString = StringBuilder().apply {
            hashBytes.forEach { byte ->
                append(String.format("%02x", byte))
            }
        }

        return hexString.toString()
    }

    private fun getMessageDigest(): MessageDigest =
        MessageDigest.getInstance(HASH_ALGORITHM)

    private const val HASH_ALGORITHM = "SHA-256"
}
