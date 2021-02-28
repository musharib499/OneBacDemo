package com.app.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodMenu(
        @SerializedName("categories")
        var categories: List<Category?>?
) : Parcelable {
    @Parcelize
    data class Category(
            @SerializedName("details")
            var details: Details?,
            @SerializedName("dishName")
            var dishName: String?, // Chinese
            @SerializedName("image")
            var image: String?, // https://www.cookwithmanali.com/wp-content/uploads/2014/11/Hakka-Noodles-1-500x375.jpg
            @SerializedName("name")
            var name: String? // Restro A1
    ) : Parcelable {
        @Parcelize
        data class Details(
                @SerializedName("famous")
                var famous: List<Famou>?,
                @SerializedName("foodItem")
                var foodItem: List<FoodItem>?
        ) : Parcelable {
            @Parcelize
            data class Famou(
                    @SerializedName("count")
                    var count: Int = 1, // 0
                    @SerializedName("dishName")
                    var dishName: String?, // Chowmein
                    @SerializedName("image")
                    var image: String?, // https://www.cookwithmanali.com/wp-content/uploads/2014/11/Hakka-Noodles-1-500x375.jpg
                    @SerializedName("price")
                    var price: Int?, // 100
                    @SerializedName("rating")
                    var rating: Int? // 4
            ) : Parcelable

            @Parcelize
            data class FoodItem(
                    @SerializedName("count")
                    var count: Int = 1, // 0
                    @SerializedName("dishName")
                    var dishName: String?, // Chowmein
                    @SerializedName("image")
                    var image: String?, // https://www.cookwithmanali.com/wp-content/uploads/2014/11/Hakka-Noodles-1-500x375.jpg
                    @SerializedName("price")
                    var price: Int = 0, // 100
                    @SerializedName("rating")
                    var rating: Int = 0 // 4
            ) : Parcelable
        }
    }
}