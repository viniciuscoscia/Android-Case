package br.com.viniciuscoscia.truckpad.domain.entities

data class RouteCalc(
        val distanceMeters: Int,
        val placeOfOrigin: String,
        val placeOfDestiny: String,
        val axis: Int,
        val tolCost: Float,
        val necessaryFuelLiters: Float,
        val totalFuelCost: Float,
        val totalCost: Float
)