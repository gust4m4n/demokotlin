package com.demokotlin.android.DemoViewModels
import android.os.Handler
import android.os.Looper
import androidx.core.view.isVisible
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class DemoApi {

    companion object {

        fun POST(url: String, params: HashMap<String,String>, headers: HashMap<String,String>, completion: (statusCode: Int, errMsg: String, json: JSONObject, raw: String) -> Unit) {
            val client = OkHttpClient()

            val builder = FormBody.Builder()
            val it = params.entries.iterator()
            while (it.hasNext()) {
                val pair = it.next() as Map.Entry<*, *>
                builder.add(pair.key.toString(), pair.value.toString())
            }
            val formBody = builder.build()

            val headersBuilder = Headers.Builder()
            val ith = headers.entries.iterator()
            while (ith.hasNext()) {
                val pair = ith.next() as Map.Entry<*, *>
                headersBuilder.add(pair.key.toString(), pair.value.toString())
            }
            val head = headersBuilder.build()

            val request = Request.Builder()
                .url(url)
                .post(formBody)
                .headers(head)
                .build()

            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Handler(Looper.getMainLooper()).post {
                        completion.invoke(-1, e.toString(), JSONObject(), "")
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        val raw = response.body?.string()
                        val json = JSONObject(raw!!)
                        println("[RESPONSE] ${json.toString(4)}")
                        if (!response.isSuccessful) {
                            Handler(Looper.getMainLooper()).post {
                                completion.invoke(response.code, response.toString(), json, raw)
                            }
                        } else {
                            Handler(Looper.getMainLooper()).post {
                                completion.invoke(response.code, response.toString(), json, raw)
                            }
                        }
                    }
                }
            })
        }

        fun GET(url: String, completion: (statusCode: Int, errMsg: String, json: JSONObject, raw: String) -> Unit) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Handler(Looper.getMainLooper()).post {
                        completion.invoke(-1, e.toString(), JSONObject(), "")
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) {
                            Handler(Looper.getMainLooper()).post {
                                completion.invoke(response.code, response.toString(), JSONObject(), "")
                            }
                        } else {
                            val raw = response.body?.string()
                            val json = JSONObject(raw!!)
                            Handler(Looper.getMainLooper()).post {
                                println("[RESPONSE] ${json.toString(4)}")
                                completion.invoke(response.code, response.toString(), json, raw)
                            }
                        }
                    }
                }
            })
        }

    }

}