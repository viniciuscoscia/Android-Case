package br.com.viniciuscoscia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes_calc_results")
data class RoutesCalcCache(
        @PrimaryKey
        val id: Int,
        val distanceMeters: Int,
        val placeOfOrigin: String,
        val placeOfDestiny: String,
        val axis: Int,
        val tolCost: Float,
        val necessaryFuelLiters: Float,
        val totalFuelCost: Float,
        val totalCost: Float,
        val priceByCargoTypeList: List<Pair<String, Float>>
)