package com.demokotlin.StringMD5Ext
import java.security.MessageDigest

fun String.md5(): String {
    return MessageDigest.getInstance("MD5").digest(this.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}

