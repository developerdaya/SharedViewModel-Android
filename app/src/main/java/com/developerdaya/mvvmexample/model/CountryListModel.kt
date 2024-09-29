package com.developerdaya.mvvmexample.model
import com.google.gson.annotations.SerializedName

data class CountryListModel(
    @SerializedName("data")
    val `data`: ArrayList<Data>
) {
    data class Data(
        @SerializedName("counties")
        val counties: Int,
        @SerializedName("detail")
        val detail: String,
        @SerializedName("population")
        val population: Int,
        @SerializedName("state")
        val state: String,
        @SerializedName("isSelected")
        var isSelected: Boolean = true,
    )
}