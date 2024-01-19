package com.demokotlin.android.DemoViewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.demokotlin.StringAES256Ext.decryptAES256
import com.demokotlin.StringAES256Ext.encryptAES256
import com.demokotlin.StringCRCExt.crc64
import com.demokotlin.StringMD5Ext.md5
import com.demokotlin.StringSHA256Ext.sha256
import com.demokotlin.StringXORExt.encryptXOR

class DemoPreferencesVM {

    companion object {
        fun getPreferences() : SharedPreferences {
            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            return EncryptedSharedPreferences.create(
                "BioPreferences", masterKeyAlias, DemoApplication.applicationContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

        fun generateKeyPassword() : String {
            val base1 = "c6d2c887525cb96beea25858deb509e6068cfdfa95db94a3cbc98ecfc061338f"
            val base2 = (base1.substring(0, base1.count() / 3).md5()).sha256()
            val base3 = (base2.encryptXOR(base1.substring(0, base1.count() / 2))).sha256()
            val base4 = (base3.crc64().toString()).sha256()
            val base5 = (base4.encryptAES256(base4.sha256())).sha256()
            return (base1 + base2 + base3 + base4 + base5).sha256()
        }

        fun generateValuePassword(key: String) : String {
            val base1 = "7354748eda7b162bf420c037fa7d352c6bf83d65f4e794d72130d55abf49cabb"
            val base2 = (base1.substring(0, base1.count() / 3).md5()).sha256()
            val base3 = (base2.encryptXOR(base1.substring(0, base1.count() / 2))).sha256()
            val base4 = (base3.crc64().toString()).sha256()
            val base5 = (base4.encryptAES256(base4.sha256())).sha256()
            return (base1 + base2 + base3 + base4 + base5 + key).sha256()
        }

        fun encryptKey(key: String) : String {
            return key.encryptXOR(generateKeyPassword())
        }

        fun encryptValue(value: String, key: String) : String {
            return value.encryptAES256(generateValuePassword(key))
        }

        fun decryptValue(value: String, key: String) : String {
            return value.decryptAES256(generateValuePassword(key))
        }

        fun setString(key: String, value: String) {
            getPreferences().edit().apply {
                val mkey = encryptKey(key)
                putString(mkey, encryptValue(value, mkey))
            }.apply()
        }

        fun getString(key: String) : String {
            val mkey = encryptKey(key)
            val value = getPreferences().getString(mkey, "")
            value?.let {
                return decryptValue(it, mkey)
            }
            return ""
        }

        fun setLong(key: String, value: Long) {
            setString(key, value.toString())
        }

        fun getLong(key: String) : Long {
            return getString(key).toLong()
        }

        fun setBoolean(key: String, value: Boolean) {
            setString(key, value.toString())
        }

        fun getBoolean(key: String) : Boolean {
            return getString(key).toBoolean()
        }

        fun remove(context: Context, key: String) {
            val mkey = encryptKey(key)
            getPreferences().edit().apply {
                remove(mkey)
            }.apply()
        }

        fun clear() {
            getPreferences().edit().apply {
                clear()
            }.apply()
        }

        fun setUserEmail(value: String) {
            setString("fd42de48823300e869f45824dfee96f22eebf312b88a31d3af4999c705703a6c", value)
        }

        fun getUserEmail() : String {
            return getString("fd42de48823300e869f45824dfee96f22eebf312b88a31d3af4999c705703a6c")
        }

        fun setUserToken(value: String) {
            setString("331156fcd14d067ff1e31d224a259a4259edec45b02f1c4d90e57dd02a2f7139", value)
        }

        fun getUserToken() : String {
            return getString("331156fcd14d067ff1e31d224a259a4259edec45b02f1c4d90e57dd02a2f7139")
        }

        fun setUserAvatar(value: String) {
            setString("da9157046289ee6d4fded0e485f0e462ca5f0ad62bdef0e549a68c4eb55acc4a", value)
        }

        fun getUserAvatar() : String {
            return getString("da9157046289ee6d4fded0e485f0e462ca5f0ad62bdef0e549a68c4eb55acc4a")
        }

        fun setActivated(value: Boolean) {
            setBoolean("e81cc07a802bc8f5954b8356f422bac06ad4a34b45f82ec09bc1af324970fbfc", value)
        }

        fun getActivated() : Boolean {
            return getBoolean("e81cc07a802bc8f5954b8356f422bac06ad4a34b45f82ec09bc1af324970fbfc")
        }

    }

}