package br.com.viniciuscoscia.data.api

import br.com.viniciuscoscia.data.entities.route.RouteRequest
import br.com.viniciuscoscia.data.entities.route.RouteResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TruckPadGeoAPI {

    @POST(ROUTE_ENDPOINT)
    suspend fun fetchRoute(
        @Body routeRequest: RouteRequest
    ): RouteResponse

    companion object {
        private const val ROUTE_ENDPOINT = "/route"
    }
}