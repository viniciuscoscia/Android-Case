package br.com.viniciuscoscia.truckpad.domain.usecases

import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import br.com.viniciuscoscia.truckpad.domain.entities.RouteCalc
import br.com.viniciuscoscia.truckpad.domain.repositories.ITruckPadRepository

class GetRouteCalcsUseCase(
        private val repository: ITruckPadRepository
) : BaseUseCase<RouteCalc, GetRouteCalcsUseCase.Params>() {

    override suspend fun execute(params: Params): RouteCalc {
        return with(params) {
            repository.fetchRouteCalcs(
                    fuelConsumptionKilometersPerLiter,
                    fuelPrice,
                    places)
        }
    }

    data class Params(
            val fuelConsumptionKilometersPerLiter: Int,
            val fuelPrice: Double,
            val places: List<Coordinate>
    )
}