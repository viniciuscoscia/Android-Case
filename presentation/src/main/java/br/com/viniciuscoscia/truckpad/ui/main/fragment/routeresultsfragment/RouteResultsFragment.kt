package br.com.viniciuscoscia.truckpad.ui.main.fragment.routeresultsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.viniciuscoscia.truckpad.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class RouteResultsFragment : Fragment() {

    private val viewModel: RouteResultsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.route_results_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        fun newInstance() = RouteResultsFragment()
    }
}
