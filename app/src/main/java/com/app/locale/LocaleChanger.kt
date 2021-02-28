package com.app.locale

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocaleChanger {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private const val SELECTED_COUNTRY = "Locale.Helper.Selected.Country"
    fun wrapContext(context: Context): Context {
        val savedLocale = getLocaleFromPref(context) ?: return context
        Locale.setDefault(savedLocale)

        val newConfig = Configuration(context.resources.configuration)
        newConfig.setLocale(savedLocale)
        return context.createConfigurationContext(newConfig)
    }

    fun overrideLocale(context: Context) {
        val savedLocale = getLocaleFromPref(context) ?: return
        Locale.setDefault(savedLocale)
        val newConfig = Configuration(context.resources.configuration)
        newConfig.setLocale(savedLocale)


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            context.createConfigurationContext(newConfig)
        } else {

            // override the locale on the given context (Activity, Fragment, etc...)
            context.resources.updateConfiguration(newConfig, context.resources.displayMetrics)

            // override the locale on the application context
            if (context != context.applicationContext) {
                context.applicationContext.resources.run {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                        context.applicationContext.createConfigurationContext(newConfig)
                    } else {
                        updateConfiguration(newConfig, displayMetrics)
                    }
                }
            }
        }
    }

    fun getLocaleFromPref(context: Context): Locale {
        val preferences = getPreferences(context)
        val language = preferences.getString(SELECTED_LANGUAGE, Locale.getDefault().language)
        val country = preferences.getString(SELECTED_COUNTRY, Locale.getDefault().country)
        return Locale(language, country)
    }

    fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(LocaleChanger::class.java.name, Context.MODE_PRIVATE)
    }

    private fun persist(context: Context, locale: Locale?) {
        if (locale == null) return
        getPreferences(context)
                .edit()
                .putString(SELECTED_LANGUAGE, locale.language)
                .putString(SELECTED_COUNTRY, locale.country)
                .apply()
    }

    fun setLocale(context: Context, locale: Locale) {
        persist(context, locale)
        wrapContext(context)
    }
}