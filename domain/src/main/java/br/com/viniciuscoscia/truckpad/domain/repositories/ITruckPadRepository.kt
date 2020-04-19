package br.com.viniciuscoscia.truckpad.domain.repositories

import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import br.com.viniciuscoscia.truckpad.domain.entities.PriceByCargoType
import br.com.viniciuscoscia.truckpad.domain.entities.RouteCalc

interface ITruckPadRepository {

    suspend fun fetchRouteCalcs(fuelConsumptionKilometersPerLiter: Int,
                                fuelPrice: Double,
                                places: List<Coordinate>): RouteCalc

    suspend fun fetchPricesByCargoType(axis: Int,
                                       distanceMeters: Double,
                                       hasReturnShipment: Boolean): PriceByCargoType

}