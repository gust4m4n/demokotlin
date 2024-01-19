package com.demokotlin.android
import org.json.JSONObject

class DemoArticleModel {
    var identifier: Long = 0
    var created_at: String = ""
    var updated_at: String = ""
    var title: String = ""
    var body: String = ""
    var image_url: String = ""
    var video_url: String = ""
    var category: String = ""
    var tag: String = ""
    var view_count: Long = 0
    var likes_count: Long = 0

    constructor(json: JSONObject) {
        this.identifier = json.getLong("id")
        this.created_at = json.getString("created_at")
        this.updated_at = json.getString("updated_at")
        this.title = json.getString("title")
        this.body = json.getString("body")
        this.image_url = json.getString("image_url")
        this.video_url = json.getString("video_url")
        this.category = json.getString("category")
        this.tag = json.getString("tag")
        this.view_count = json.getLong("view_count")
        this.likes_count = json.getLong("likes_count")
    }
}
