package com.demokotlin.android.DemoViewModels
import com.demokotlin.android.DemoArticleModel
import org.json.JSONObject
import java.util.HashMap

class DemoArticleListVM {
    var list = ArrayList<DemoArticleModel>()

    fun request(completion: (statusCode: Int, errMsg: String, json: JSONObject) -> Unit) {

        val headers: HashMap<String, String> = HashMap<String,String>()
        val params: HashMap<String, String> = HashMap<String,String>()
        params.put("filter", "")
        params.put("category", "")
        params.put("tag", "")
        params.put("page", "1")
        params.put("per_page", "128")

        DemoApi.POST("https://api.demokotlin.com/article/list", params, headers) { statusCode: Int, errMsg: String, json: JSONObject, raw: String ->
            this.list.clear()
            val result = json.getJSONObject("result")
            val data = result.getJSONArray("data")
            for (i in 0 until data.length()) {
                val item = DemoArticleModel(data.getJSONObject(i))
                this.list.add(item)
            }
            completion.invoke(statusCode, errMsg, json)
        }
    }

}