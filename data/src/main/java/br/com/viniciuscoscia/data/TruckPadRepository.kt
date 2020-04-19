package br.com.viniciuscoscia.data

import br.com.viniciuscoscia.data.local.source.PriceByCargoTypeDataSourceCache
import br.com.viniciuscoscia.data.local.source.RoutesCalcDataSourceCache
import br.com.viniciuscoscia.data.remote.mapper.PriceByCargoTypeMapper
import br.com.viniciuscoscia.data.remote.mapper.RouteCalcsMapper
import br.com.viniciuscoscia.data.remote.model.pricebycargotype.PriceByCargoTypeRequest
import br.com.viniciuscoscia.data.remote.model.route.RouteRequest
import br.com.viniciuscoscia.data.remote.source.PriceByCargoTypeSourceRemote
import br.com.viniciuscoscia.data.remote.source.RoutesCalcSourceRemote
import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import br.com.viniciuscoscia.truckpad.domain.entities.PriceByCargoType
import br.com.viniciuscoscia.truckpad.domain.entities.RouteCalc
import br.com.viniciuscoscia.truckpad.domain.repositories.ITruckPadRepository

class TruckPadRepository(
        private val priceByCargoTypeCache: PriceByCargoTypeDataSourceCache,
        private val priceByCargoTypeSourceRemote: PriceByCargoTypeSourceRemote,
        private val routesCalcCache: RoutesCalcDataSourceCache,
        private val routesCalcSourceRemote: RoutesCalcSourceRemote
) : ITruckPadRepository {
    override suspend fun fetchRouteCalcs(fuelConsumptionKilometersPerLiter: Int,
                                         fuelPrice: Double,
                                         places: List<Coordinate>): RouteCalc {
        val routeRequest = RouteRequest(fuelConsumption = fuelConsumptionKilometersPerLiter,
                fuelPrice = fuelPrice,
                places = RouteCalcsMapper.coordinateListToPlaceList(places))

        return RouteCalcsMapper.map(routesCalcSourceRemote.fetchRoute(routeRequest))
    }

    override suspend fun fetchPricesByCargoType(axis: Int,
                                                distanceMeters: Double,
                                                hasReturnShipment: Boolean): PriceByCargoType {
        val priceByCargoTypeRequest = PriceByCargoTypeRequest(axis,
                distanceMeters,
                hasReturnShipment)

        return PriceByCargoTypeMapper
                .map(priceByCargoTypeSourceRemote.fetchPricesByTruckCargoType(priceByCargoTypeRequest))
    }
}