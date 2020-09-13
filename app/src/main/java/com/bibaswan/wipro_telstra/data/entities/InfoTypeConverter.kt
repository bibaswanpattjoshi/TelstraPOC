package com.bibaswan.wipro_telstra.data.entities
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class InfoTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<CanadaInfo> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<CanadaInfo>>() {

        }.type

        return gson.fromJson<List<CanadaInfo>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<CanadaInfo>): String {
        return gson.toJson(someObjects)
    }
}