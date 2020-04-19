package br.com.viniciuscoscia.data.remote.model.route


import com.google.gson.annotations.SerializedName

data class RouteRequest(
        @SerializedName("places")
    val places: List<Place> = listOf(),
        @SerializedName("fuel_consumption")
        val fuelConsumption: Double = 0.0,
        @SerializedName("fuel_price")
    val fuelPrice: Double = 0.0
)