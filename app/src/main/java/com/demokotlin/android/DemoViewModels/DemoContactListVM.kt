package com.demokotlin.android.DemoViewModels
import com.demokotlin.android.DemoContactModel
import org.json.JSONObject

class DemoContactListVM {
    var list = ArrayList<DemoContactModel>()

    fun request(completion: (statusCode: Int, errMsg: String, json: JSONObject) -> Unit) {
        DemoApi.GET("https://api.demokotlin.com/files/DemoContactList3.json") { statusCode: Int, errMsg: String, json: JSONObject, raw: String ->
            this.list.clear()
            val result = json.getJSONArray("result")
            for (i in 0 until result.length()) {
                val item = DemoContactModel(result.getJSONObject(i))
                this.list.add(item)
            }
            completion.invoke(statusCode, errMsg, json)
        }
    }

}