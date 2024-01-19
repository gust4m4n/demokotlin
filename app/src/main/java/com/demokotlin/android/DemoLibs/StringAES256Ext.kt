package com.demokotlin.StringAES256Ext
import com.demokotlin.StringXORExt.decryptXOR
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest
import java.util.*

fun String.transformationAES256() : String {
    // "AES/ECB/PKCS5Padding"
    return "cSYxSiEgJhpncnFlAzFTUwFaXwY=".decryptXOR("0cbedcd579266a27e31a028ab7df807abd6b0c767a9cf6841d06d0c4d7b29619")
}

fun String.algorithmAES256() : String {
    // "AES"
    return "ciBr".decryptXOR("3e8807858ecbaffc1bf16e29f4cb57b3b53d5b6305eadf64ed4c210a10436e10")
}

fun String.digestAES256() : String {
    // "SHA-256"
    return "MCogGgUFAw==".decryptXOR("cba77053279da9f14a30b2d8faf28c6b16e9eae01400780ec897ec700ca5459c")
}

fun String.charsetAES256() : String {
    // "UTF-8"
    return "MGEkGwg=".decryptXOR("e5b60af6b04dea5f013e48a612611859bb141d7a55008d74a65c5f0e75675eee")
}

fun String.encryptAES256(key: String): String {
    if (this.count() == 0 || key.count() == 0) {
        return ""
    }
    try {
        var mkey = key.toByteArray(charset(charsetAES256()))
        val sha = MessageDigest.getInstance(digestAES256())
        mkey = sha.digest(mkey)
        mkey = Arrays.copyOf(mkey, 32)
        val secretKey = SecretKeySpec(mkey, algorithmAES256())
        val cipher = Cipher.getInstance(transformationAES256())
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return Base64.getEncoder().encodeToString(cipher.doFinal
            (this.toByteArray(charset("UTF-8"))))
    } catch (e: Exception) {
    }
    return ""
}

fun String.decryptAES256(key: String): String {
    if (this.count() == 0 || key.count() == 0) {
        return ""
    }
    try {
        var mkey = key.toByteArray(charset(charsetAES256()))
        val sha = MessageDigest.getInstance(digestAES256())
        mkey = sha.digest(mkey)
        mkey = Arrays.copyOf(mkey, 32)
        val secretKey = SecretKeySpec(mkey,  algorithmAES256())
        val cipher = Cipher.getInstance(transformationAES256())
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        return String(cipher.doFinal(Base64.getDecoder().decode(this)))
    } catch (e: Exception) {
    }
    return ""
}
