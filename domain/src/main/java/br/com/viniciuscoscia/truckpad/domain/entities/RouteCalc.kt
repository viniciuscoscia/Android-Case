package br.com.viniciuscoscia.truckpad.domain.entities

data class RouteCalc(
        val distanceMeters: Int,
        val placeOfOrigin: String,
        val placeOfDestiny: String,
        val tollCost: Double,
        val tollCostUnit: String,
        val necessaryFuel: Double,
        val necessaryFuelUnity: String,
        val totalFuelCost: Double,
        val totalCost: Double
)