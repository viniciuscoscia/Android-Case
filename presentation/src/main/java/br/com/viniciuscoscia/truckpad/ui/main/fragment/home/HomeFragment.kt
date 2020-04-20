package br.com.viniciuscoscia.truckpad.ui.main.fragment.home

import android.location.Address
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.viniciuscoscia.truckpad.R
import br.com.viniciuscoscia.truckpad.common.textNotBlank
import br.com.viniciuscoscia.truckpad.common.textToDouble
import br.com.viniciuscoscia.truckpad.common.textToInt
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
        setupListeners()
    }

    private fun executeCalc() {
        val origin = viewModel.searchAddresses(etOrigin.text.toString())
        val destiny = viewModel.searchAddresses(etDestiny.text.toString())
        val axis = etAxisNumber.textToInt()

        if (areFieldsValid(axis, origin, destiny)) {
            val calcResults = viewModel.searchRoutesAndPrices(
                etFuelConsumption.textToInt(),
                etFuelPricePerLiter.textToDouble(),
                origin[0],
                destiny[0],
                axis
            )

            val action =
                HomeFragmentDirections.actionNavigationHomeToRouteResultsFragment(calcResults)
            requireView().findNavController().navigate(action)
        }
    }

    private fun isDestinyValid(address: List<Address>, textInputLayout: TextInputLayout) =
        if (address.isEmpty()) {
            textInputLayout.error = getString(R.string.insert_a_destiny)
            false
        } else {
            true
        }


    private fun isAxisNumberValid(axis: Int) =
        if (viewModel.validateAxisQuantity(axis).not()) {
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
            PlaceState.ERROR -> Toast.makeText(
                requireContext(),
                placeResult.errorMessage,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun areFieldsValid(
        axis: Int,
        origin: List<Address>,
        destiny: List<Address>
    ): Boolean {
        var areValid = true

        if (isAxisNumberValid(axis).not()) {
            areValid = false
        }
        if (isDestinyValid(origin, tilOrigin).not()) {
            areValid = false
        }
        if (isDestinyValid(destiny, tilDestiny).not()) {
            areValid = false
        }
        if (etFuelConsumption.textNotBlank().not()) {
            tilFuelConsumption.error = getString(R.string.insert_a_value)
            areValid = false
        }
        if (etFuelPricePerLiter.textNotBlank().not()) {
            tilFuelPricePerLiter.error = getString(R.string.insert_a_value)
            areValid = false
        }

        return areValid
    }

    private fun setupListeners() {
        tilOrigin.setEndIconOnClickListener { viewModel.searchForOriginAddress(etOrigin.text.toString()) }
        tilDestiny.setEndIconOnClickListener { viewModel.searchForDestinyAddress(etDestiny.text.toString()) }

        etOrigin.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not()) {
                viewModel.searchForOriginAddress(etOrigin.text.toString())
            }
        }

        etDestiny.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not()) {
                viewModel.searchForDestinyAddress(etDestiny.text.toString())
            }
        }

        etAxisNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not() && etAxisNumber.text.toString().isNotBlank()) {
                isAxisNumberValid(etAxisNumber.text.toString().toInt())
            }
        }

        btnCalculate.setOnClickListener {
            executeCalc()
        }

        etOrigin.doOnTextChanged { _, _, _, _ ->
            tilOrigin.isErrorEnabled = false
        }
        etDestiny.doOnTextChanged { _, _, _, _ ->
            tilDestiny.isErrorEnabled = false
        }
        etAxisNumber.doOnTextChanged { _, _, _, _ ->
            tilAxisNumber.isErrorEnabled = false
        }
        etFuelPricePerLiter.doOnTextChanged { _, _, _, _ ->
            tilFuelPricePerLiter.isErrorEnabled = false
        }
        etFuelConsumption.doOnTextChanged { _, _, _, _ ->
            tilFuelConsumption.isErrorEnabled = false
        }
    }
}
