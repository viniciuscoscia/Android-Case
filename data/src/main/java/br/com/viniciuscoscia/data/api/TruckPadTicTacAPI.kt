package br.com.viniciuscoscia.data.api

import br.com.viniciuscoscia.data.entities.pricebycargotype.PriceByCargoTypeRequest
import br.com.viniciuscoscia.data.entities.pricebycargotype.PriceByCargoTypeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TruckPadTicTacAPI {

    @POST(VALUES_BY_TRUCK_CARGO_TYPE_ENDPOINT)
    fun fetchValuesByTruckCargoType(
        @Body priceByCargoTypeRequest: PriceByCargoTypeRequest
    ): PriceByCargoTypeResponse

    companion object {
        const val VALUES_BY_TRUCK_CARGO_TYPE_ENDPOINT = "/antt_price/all"
    }
}