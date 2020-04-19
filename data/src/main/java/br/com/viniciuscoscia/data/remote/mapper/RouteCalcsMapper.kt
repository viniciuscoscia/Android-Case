package br.com.viniciuscoscia.data.remote.mapper

import br.com.viniciuscoscia.data.remote.model.route.Place
import br.com.viniciuscoscia.data.remote.model.route.RouteResponse
import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import br.com.viniciuscoscia.truckpad.domain.entities.RouteCalc

object RouteCalcsMapper {
    fun map(routeResponse: RouteResponse) = RouteCalc(
            distanceMeters = routeResponse.distance,
            placeOfOrigin = routeResponse.points[0].provider,
            placeOfDestiny = routeResponse.points[1].provider,
            tollCost = routeResponse.tollCost,
            tollCostUnit = routeResponse.tollCostUnit,
            necessaryFuel = routeResponse.fuelUsage,
            necessaryFuelUnity = routeResponse.fuelCostUnit,
            totalFuelCost = 0.0,
            totalCost = routeResponse.totalCost
    )

    fun coordinateListToPlaceList(coordinates: List<Coordinate>): List<Place> = coordinates.map {
        Place(listOf(it.longitude, it.latitude))
    }
}