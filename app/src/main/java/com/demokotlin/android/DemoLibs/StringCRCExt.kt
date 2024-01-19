package com.demokotlin.StringCRCExt

fun String.crc64() : Long {
    if (this.count() == 0) {
        return 0
    }
    val dataArray = this.toCharArray()
    val ml = dataArray!!.size
    var value: Long = 0
    for (i in 0 until ml) {
        value = value + dataArray[i].code
    }
    return value
}

