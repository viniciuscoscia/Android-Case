package br.com.viniciuscoscia.truckpad.domain.repositories

import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import br.com.viniciuscoscia.truckpad.domain.entities.RouteCalc

interface IRoutesRepository {

    fun fetchRouteCalcs(fuelConsumptionKilometersPerLiter: Float,
                        fuelPrice: Float,
                        places: List<Coordinate>): RouteCalc

    fun fetchPricesByCargoType(axis: Int,
                               distanceMeters: Float,
                               hasReturnShipment: Boolean): List<Pair<String, Float>>

}