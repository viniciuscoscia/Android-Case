package br.com.viniciuscoscia.truckpad.domain.entities

data class RouteCalcResults(
    val distanceMeters: Int,
    val priceList: List<Pair<String, Float>>
    
)