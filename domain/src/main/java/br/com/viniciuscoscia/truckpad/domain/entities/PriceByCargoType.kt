package br.com.viniciuscoscia.truckpad.domain.entities

data class PriceByCargoType (
        val frigorificada: Float,
        val geral: Float,
        val granel: Float,
        val neogranel: Float,
        val perigosa: Float
)