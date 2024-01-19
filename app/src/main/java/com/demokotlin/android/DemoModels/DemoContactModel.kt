package com.demokotlin.android
import org.json.JSONObject

class DemoContactModel {
    var identifier: Long = 0
    var fullname: String = ""
    var phone: String = ""
    var avatar: String = ""

    constructor(json: JSONObject) {
        this.identifier = json.getLong("id")
        this.fullname = json.getString("fullname")
        this.phone = json.getString("phone")
        this.avatar = json.getString("avatar")
    }
}
