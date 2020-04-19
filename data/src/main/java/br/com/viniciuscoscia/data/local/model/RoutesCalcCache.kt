package br.com.viniciuscoscia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes_calc_results")
data class RoutesCalcCache(
        @PrimaryKey
        var id: Int,
        var distanceMeters: Int,
        var placeOfOrigin: String,
        var placeOfDestiny: String,
        var axis: Int,
        var tolCost: Double,
        var necessaryFuelLiters: Double,
        var totalFuelCost: Double,
        var totalCost: Double
)