package com.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.app.locale.LocaleChanger
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        LocaleChanger.setLocale(baseContext, LocaleChanger.getLocaleFromPref(this))
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleChanger.overrideLocale(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocaleChanger.wrapContext(it) })
    }
}
