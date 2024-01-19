package com.demokotlin.StringBase64Ext
import java.util.*

fun String.encodeBase64(): String {
    if (this.count() == 0) {
        return ""
    }
    return Base64.getEncoder().encodeToString(this.toByteArray())
}

fun String.decodeBase64(): String {
    if (this.count() == 0) {
        return ""
    }
    return String(Base64.getDecoder().decode(this))
}

