package br.com.viniciuscoscia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Price_by_cargo_type")
data class PriceByCargoTypeCache(
        @PrimaryKey
        var id: Int = 0,
        var frigorificada: Float,
        var geral: Float,
        var granel: Float,
        var neogranel: Float,
        var perigosa: Float
)