package br.com.viniciuscoscia.data.entities.route


import com.google.gson.annotations.SerializedName

data class RouteRequest(
    @SerializedName("places")
    val places: List<Place> = listOf(),
    @SerializedName("fuel_consumption")
    val fuelConsumption: Int = 0,
    @SerializedName("fuel_price")
    val fuelPrice: Double = 0.0
)