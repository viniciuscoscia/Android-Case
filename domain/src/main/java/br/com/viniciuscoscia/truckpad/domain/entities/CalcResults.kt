package br.com.viniciuscoscia.truckpad.domain.entities

import java.io.Serializable

data class CalcResults(
    val axis: Int,
    val placeOfOriginName: String,
    val placeOfOriginLatitude: Float,
    val placeOfOriginLongitude: Float,
    val placeOfDestinyName: String,
    val placeOfDestinyLatitude: Float,
    val placeOfDestinyLongitude: Float,
    val duration: Int,
    val durationUnit: String,
    val fuelConsumption: Double,
    val fuelUnity: String,
    val fuelPrice: Double,
    val distance: Int,
    val distanceUnit: String,
    val tollCost: Double,
    val tollCostUnit: String,
    val necessaryFuel: Double,
    val necessaryFuelUnity: String,
    val totalFuelCost: Double,
    val totalFuelCostUnit: String,
    val totalCost: Double,
    val frigorificada: Double,
    val geral: Double,
    val granel: Double,
    val neogranel: Double,
    val perigosa: Double,
    val timeStamp: Long
) : Serializable