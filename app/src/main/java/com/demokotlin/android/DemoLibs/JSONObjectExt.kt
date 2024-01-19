package com.demokotlin.JSONObjectExt
import org.json.JSONException
import org.json.JSONObject

fun JSONObject.getJSONObjectValue(name: String) : JSONObject {
    try {
        return this.getJSONObject(name)
    } catch (e: JSONException) {
        return JSONObject()
    } finally {
    }
}

fun JSONObject.getStringValue(name: String) : String {
    try {
        return this.getString(name)
    } catch (e: JSONException) {
        return ""
    } finally {
    }
}

fun JSONObject.getLongValue(name: String) : Long {
    try {
        return this.getLong(name)
    } catch (e: JSONException) {
        return 0
    } finally {
    }
}

fun JSONObject.getBooleanValue(name: String) : Boolean {
    try {
        return this.getBoolean(name)
    } catch (e: JSONException) {
        return false
    } finally {
    }
}

