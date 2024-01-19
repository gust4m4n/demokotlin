package com.demokotlin.android.DemoViewModels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.demokotlin.android.BuildConfig

class DemoApplication : Application(), Application.ActivityLifecycleCallbacks {
    init {
        instance = this
    }

    companion object {
        private var instance: DemoApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        LOG("[DemoApplication] onCreate")
        this.registerActivityLifecycleCallbacks(this);
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        LOG("[DemoApplication] onActivityCreated")
    }

    override fun onActivityStarted(p0: Activity) {
        LOG("[DemoApplication] onActivityStarted")
    }

    override fun onActivityStopped(p0: Activity) {
        LOG("[DemoApplication] onActivityStopped")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        LOG("[DemoApplication] onActivitySaveInstanceState")
    }

    override fun onActivityResumed(p0: Activity) {
        LOG("[DemoApplication] onActivityResumed")
    }

    override fun onActivityPaused(p0: Activity) {
        LOG("[DemoApplication] onActivityPaused")
    }

    override fun onActivityDestroyed(p0: Activity) {
        LOG("[DemoApplication] onActivityDestroyed")
    }

}

fun LOG(text: String) {
    if (BuildConfig.DEBUG) {
        println("[DEMO]${text}")
    }
}