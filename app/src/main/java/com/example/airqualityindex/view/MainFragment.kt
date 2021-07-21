package com.example.airqualityindex.view

import android.os.Bundle
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
import com.example.airqualityindex.databinding.MainFragmentBinding
import com.example.airqualityindex.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var adapter: CitiesAqiRecyclerviewAdapter
    private var navController: NavController? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        return mainFragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
//        initRecyclerView()

    }

    private fun initRecyclerView() {
        mainFragmentBinding.cityAqiList.layoutManager =
            LinearLayoutManager(mainFragmentBinding.cityAqiList.context)
        adapter = CitiesAqiRecyclerviewAdapter()
        adapter.setData(viewModel.getAqi())
        mainFragmentBinding.cityAqiList.adapter = adapter
    }
}