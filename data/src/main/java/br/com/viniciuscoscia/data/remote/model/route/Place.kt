package br.com.viniciuscoscia.data.remote.model.route


import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("point")
    val point: List<Double> = listOf()
)