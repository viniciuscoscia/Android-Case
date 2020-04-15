package br.com.viniciuscoscia.truckpad.domain.usecases

import br.com.viniciuscoscia.truckpad.domain.repositories.IRoutesRepository

class GetPricesByCargoTypeUseCase(private val repository: IRoutesRepository):
        BaseUseCase<List<Pair<String, Float>>, GetPricesByCargoTypeUseCase.Params>() {

    override suspend fun execute(params: Params): List<Pair<String, Float>> {
        with(params) {
            return repository.fetchPricesByCargoType(
                    axis,
                    distanceMeters,
                    hasReturnShipment
            )
        }
    }

    data class Params(val axis: Int,
                      val distanceMeters: Float,
                      val hasReturnShipment: Boolean)
}