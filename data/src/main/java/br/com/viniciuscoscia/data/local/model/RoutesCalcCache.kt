package br.com.viniciuscoscia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.viniciuscoscia.truckpad.domain.entities.PriceByCargoType

@Entity(tableName = "routes_calc_results")
data class RoutesCalcCache(
        @PrimaryKey
        var id: Int,
        var distanceMeters: Int,
        var placeOfOrigin: String,
        var placeOfDestiny: String,
        var axis: Int,
        var tolCost: Float,
        var necessaryFuelLiters: Float,
        var totalFuelCost: Float,
        var totalCost: Float
)