package br.com.viniciuscoscia.truckpad.ui.main.fragment.home

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.viniciuscoscia.truckpad.R
import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import br.com.viniciuscoscia.truckpad.domain.entities.RouteCalc
import br.com.viniciuscoscia.truckpad.domain.usecases.GetPricesByCargoTypeUseCase
import br.com.viniciuscoscia.truckpad.domain.usecases.GetRouteCalcsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.lang.reflect.InvocationTargetException
import java.text.DecimalFormat
import java.util.*

class HomeViewModel(private val context: Context,
                    private val getRouteCalcsUseCase: GetRouteCalcsUseCase,
                    private val getPricesByCargoTypeUseCase: GetPricesByCargoTypeUseCase)
    : ViewModel() {

    private val geoCoder = Geocoder(context, Locale("pt", "BR"))

    private val _originValidator = MutableLiveData<PlaceResult>()
    val originValidator: LiveData<PlaceResult> = _originValidator

    private val _destinyValidator = MutableLiveData<PlaceResult>()
    val destinyValidator: LiveData<PlaceResult> = _destinyValidator

    @Throws(InvocationTargetException::class)
    fun searchAddresses(text: String): List<Address> {
        if (text.isBlank()) {
            return emptyList()
        }
        return runBlocking(Dispatchers.IO) {
            geoCoder.getFromLocationName(text, 1)
        }
    }

    fun searchForOriginAddress(text: String?) {
        if (text.isNullOrBlank().not()) {
            executeSearch(text!!, _originValidator)
        }
    }

    fun searchForDestinyAddress(text: String?) {
        if (text.isNullOrBlank().not()) {
            executeSearch(text!!, _destinyValidator)
        }
    }

    fun searchRoutesAndPrices(fuelConsumptionKilometersPerLiter: Int,
                              fuelPrice: Double,
                              places: List<Coordinate>,
                              axis: Int) {
        runBlocking(Dispatchers.IO) {
            val routeCalcsParams = GetRouteCalcsUseCase.Params(
                    fuelConsumptionKilometersPerLiter,
                    fuelPrice,
                    places
            )
            val routeCalc: RouteCalc = getRouteCalcsUseCase.execute(routeCalcsParams)

            val pricesParams = GetPricesByCargoTypeUseCase.Params(
                    axis,
                    routeCalc.distanceMeters.toDouble(),
                    true)
            getPricesByCargoTypeUseCase.execute(pricesParams)
        }
    }

    private fun executeSearch(text: String, placeResultLiveData: MutableLiveData<PlaceResult>) {
        try {
            val addresses = searchAddresses(text)

            placeResultLiveData.value = when (addresses.size) {
                1 -> PlaceResult(
                        placeState = PlaceState.ONE_RESULT_FOUND,
                        address = addresses[0]
                )

                else -> PlaceResult(
                        placeState = PlaceState.NO_RESULT,
                        errorMessage = context.getString(R.string.place_not_found)
                )
            }
        } catch (e: InvocationTargetException) {
            placeResultLiveData.value = PlaceResult(
                    placeState = PlaceState.ERROR,
                    errorMessage = context.getString(R.string.internal_search_error)
            )
        }
    }

    fun getCoordinate(address: Address): Coordinate = with(address) {
        val df = DecimalFormat("#.#####")
        return Coordinate(df.format(latitude).toFloat(), df.format(longitude).toFloat())
    }

    fun validateAxisQuantity(axis: Int) = axis in 2..9
}

data class PlaceResult(val placeState: PlaceState,
                       val address: Address? = null,
                       val errorMessage: String = "",
                       val places: List<String> = listOf())

enum class PlaceState {
    ONE_RESULT_FOUND, NO_RESULT, ERROR
}