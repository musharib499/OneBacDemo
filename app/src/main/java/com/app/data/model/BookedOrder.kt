package com.app.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "history_table")
data class BookedOrder(
    @SerializedName("amount")
    var amount: Int = 0, // 0
    @SerializedName("count")
    var count: Int = 0, // 0
    @SerializedName("dishName")
    var dishName: String = "",
    @SerializedName("gstAmount")
    var gstAmount: Float = 0f, // 0.0
    @SerializedName("payAmount")
    var payAmount: Float = 0f, // 0.0
    @SerializedName("image")
    var image: String?="",
    @SerializedName("time")
    var time: Long = 0 // 0
): Parcelable {
    init {

    }
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = 0
}