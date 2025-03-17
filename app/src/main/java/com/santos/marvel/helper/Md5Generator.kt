package com.santos.marvel.helper

import java.security.MessageDigest

class Md5Generator {

    companion object {

        fun generateMd5Hash(timestamp: String,
                            publicKey: String,
                            privateKey: String): String {
            val inputString = "$timestamp$privateKey$publicKey"
            val bytes = MessageDigest.getInstance("MD5").digest(inputString.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }
    }
}