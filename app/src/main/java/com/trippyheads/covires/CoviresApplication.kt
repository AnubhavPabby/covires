package com.trippyheads.covires

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class CoviresApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}