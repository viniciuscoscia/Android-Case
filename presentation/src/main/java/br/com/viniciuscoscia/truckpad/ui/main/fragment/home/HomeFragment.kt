package br.com.viniciuscoscia.truckpad.ui.main.fragment.home

import android.location.Address
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.viniciuscoscia.truckpad.R
import br.com.viniciuscoscia.truckpad.common.textNotBlank
import br.com.viniciuscoscia.truckpad.common.textToDouble
import br.com.viniciuscoscia.truckpad.common.textToInt
import br.com.viniciuscoscia.truckpad.domain.entities.Coordinate
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveDatas()

        tilOrigin.setEndIconOnClickListener { viewModel.searchForOriginAddress(etOrigin.text.toString()) }
        tilDestiny.setEndIconOnClickListener { viewModel.searchForDestinyAddress(etDestiny.text.toString()) }

        etAxisNumber.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not() && etAxisNumber.text.toString().isNotBlank()) {
                isAxisNumberValid(etAxisNumber.text.toString().toInt())
            }
        }

        btnCalculate.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        val origin = viewModel.searchAddresses(etOrigin.text.toString())
        val destiny = viewModel.searchAddresses(etDestiny.text.toString())
        val axis = etAxisNumber.textToInt()

        if (isAxisNumberValid(axis)
                && isDestinyValid(origin, etOrigin)
                && isDestinyValid(destiny, etDestiny)
                && etFuelConsumption.textNotBlank()
                && etFuelPricePerLiter.textNotBlank()) {
            val originCoordinates = getCoordinate(origin[0])
            val destinyCoordinates = getCoordinate(destiny[0])

            viewModel.searchRoutesAndPrices(etFuelConsumption.textToDouble(),
                    etFuelPricePerLiter.textToDouble(),
                    listOf(originCoordinates, destinyCoordinates),
                    axis)
        }

    }

    private fun getCoordinate(address: Address) = Coordinate(address.latitude, address.longitude)

    private fun isDestinyValid(address: List<Address>, editText: TextInputEditText): Boolean {
        return if (address.isEmpty()) {
            editText.error = getString(R.string.insert_a_destiny)
            false
        } else {
            true
        }
    }


    private fun isAxisNumberValid(axis: Int) = if (viewModel.validateAxisQuantity(axis).not()) {
        tilAxisNumber.error = getString(R.string.invalid_axis_number_error)
        false
    } else {
        true
    }


    private fun setupLiveDatas() {
        viewModel.originValidator.observe(requireActivity(), Observer { placeResult ->
            dealWithPlaceResult(placeResult, tilOrigin)
        })

        viewModel.destinyValidator.observe(requireActivity(), Observer { placeResult ->
            dealWithPlaceResult(placeResult, tilDestiny)
        })
    }

    private fun dealWithPlaceResult(placeResult: PlaceResult, textInputLayout: TextInputLayout) {
        when (placeResult.placeState) {
            PlaceState.ONE_RESULT_FOUND -> {
                textInputLayout.isErrorEnabled = false
                textInputLayout.editText?.setText(placeResult.address!!.getAddressLine(0))
            }
            PlaceState.NO_RESULT -> textInputLayout.error = placeResult.errorMessage
            PlaceState.ERROR -> Toast.makeText(requireContext(),
                    placeResult.errorMessage,
                    Toast.LENGTH_LONG).show()
        }
    }
}
