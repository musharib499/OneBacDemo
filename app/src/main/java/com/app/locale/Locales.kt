package com.app.locale

import java.util.*

object Locales {
     fun getLang(lang:String): Locale = when(lang){
        "hi" -> Locale("hi","91")
        else -> Locale.ENGLISH
    }
}