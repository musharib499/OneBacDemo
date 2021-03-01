package com.app.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class Converters {

    @TypeConverter
    fun getString(someObjects: List<String>?): String? {
        return Gson().toJson(someObjects)
    }

    @TypeConverter
    fun fromStringTimestamp(data: String?): List<String>? {

        if (data == null) {
            return Collections.emptyList()
        }
        val listType =
                object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(data, listType)


    }

}