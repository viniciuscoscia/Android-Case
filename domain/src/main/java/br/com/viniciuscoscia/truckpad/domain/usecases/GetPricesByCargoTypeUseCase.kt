package br.com.viniciuscoscia.truckpad.domain.usecases

import br.com.viniciuscoscia.truckpad.domain.entities.PriceByCargoType
import br.com.viniciuscoscia.truckpad.domain.repositories.IRoutesRepository

class GetPricesByCargoTypeUseCase(private val repository: IRoutesRepository):
        BaseUseCase<PriceByCargoType, GetPricesByCargoTypeUseCase.Params>() {

    override suspend fun execute(params: Params): PriceByCargoType {
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