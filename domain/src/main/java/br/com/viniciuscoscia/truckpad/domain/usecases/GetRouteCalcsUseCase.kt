package br.com.viniciuscoscia.truckpad.domain.usecases

import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import br.com.viniciuscoscia.truckpad.domain.entities.RouteCalc
import br.com.viniciuscoscia.truckpad.domain.repositories.IRoutesRepository

class GetRouteCalcsUseCase(
        private val repository: IRoutesRepository
) : BaseUseCase<RouteCalc, GetRouteCalcsUseCase.Params>() {

    override suspend fun execute(params: Params): RouteCalc {
        with(params) {
            return repository.fetchRouteCalcs(
                    fuelConsumptionKilometersPerLiter,
                    fuelPrice,
                    places
            )
        }
    }

    data class Params(
            val fuelConsumptionKilometersPerLiter: Float,
            val fuelPrice: Float,
            val places: List<Coordinate>
    )
}