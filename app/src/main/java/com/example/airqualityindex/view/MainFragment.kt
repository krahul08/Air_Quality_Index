package com.example.airqualityindex.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airqualityindex.R
import com.example.airqualityindex.adapter.CitiesAqiRecyclerviewAdapter
import com.example.airqualityindex.adapter.FragmentCommunication
import com.example.airqualityindex.databinding.MainFragmentBinding
import com.example.airqualityindex.viewmodel.MainViewModel

class MainFragment : Fragment(), FragmentCommunication {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var adapter: CitiesAqiRecyclerviewAdapter
    private var navController: NavController? = null
    private lateinit var fragmentCommunication: FragmentCommunication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        getObserver()
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun getObserver() {
        viewModel.getAqi().observe(viewLifecycleOwner) {
            mainFragmentBinding.cityAqiList.layoutManager =
                LinearLayoutManager(mainFragmentBinding.cityAqiList.context)
            adapter = CitiesAqiRecyclerviewAdapter(requireContext(), )
            adapter.setData(it)
            mainFragmentBinding.cityAqiList.adapter = adapter
        }
    }

    override fun respond(city: String?, aqi: Double?, timeAgo: String?) {
        Log.d("afaffafa", "City:-" + city + "Aqi" + aqi + "timeAgo" + timeAgo)
    }

}