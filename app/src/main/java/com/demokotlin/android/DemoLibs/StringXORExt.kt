package com.demokotlin.StringXORExt
import com.demokotlin.StringBase64Ext.decodeBase64
import com.demokotlin.StringBase64Ext.encodeBase64

fun String.encryptXOR(key: String) : String {
    if (this.count() == 0 || key.count() == 0) {
        return ""
    }
    val keyArray = key.toCharArray()
    val dataArray = this.toCharArray()
    val ml = dataArray!!.size
    val kl = keyArray!!.size
    val newData = CharArray(ml)
    for (i in 0 until ml) {
        newData[i] = (dataArray[i].code xor keyArray[i % kl].code).toChar()
    }
    return String(newData).encodeBase64()
}

fun String.decryptXOR(key: String) : String {
    if (this.count() == 0 || key.count() == 0) {
        return ""
    }
    val keyArray = key.toCharArray()
    val dataArray = this.decodeBase64().toCharArray()
    val ml = dataArray!!.size
    val kl = keyArray!!.size
    val newData = CharArray(ml)
    for (i in 0 until ml) {
        newData[i] = (dataArray[i].code xor keyArray[i % kl].code).toChar()
    }
    return String(newData)
}