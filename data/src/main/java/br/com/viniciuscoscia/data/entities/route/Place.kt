package br.com.viniciuscoscia.data.entities.route


import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("point")
    val point: List<Double> = listOf()
)