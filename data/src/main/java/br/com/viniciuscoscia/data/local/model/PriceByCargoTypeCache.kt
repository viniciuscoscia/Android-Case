package br.com.viniciuscoscia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_by_cargo_type")
data class PriceByCargoTypeCache(
        @PrimaryKey
        var id: Int = 0,
        var frigorificada: Double,
        var geral: Double,
        var granel: Double,
        var neogranel: Double,
        var perigosa: Double
)