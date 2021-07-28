package com.example.airqualityindex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.broooapps.graphview.CurveGraphConfig
import com.example.airqualityindex.R
import com.example.airqualityindex.databinding.FragmentGraphBinding


class GraphFragment : Fragment() {

    private lateinit var graphBinding: FragmentGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        graphBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_graph, container, false)

        setGraph()
        return graphBinding.root
    }

    private fun setGraph() {

        graphBinding.curveGraphView.configure(
            CurveGraphConfig.Builder(requireContext())
                .setAxisColor(R.color.Blue) // Set number of values to be displayed in X ax
                .setVerticalGuideline(4) // Set number of background guidelines to be shown.
                .setHorizontalGuideline(2)
                .setGuidelineColor(R.color.Red) // Set color of the visible guidelines.
                .setNoDataMsg(" No Data ") // Message when no data is provided to the view.
                .setxAxisScaleTextColor(R.color.Black) // Set X axis scale text color.
                .setyAxisScaleTextColor(R.color.Black) // Set Y axis scale text color
                .setAnimationDuration(2000) // Set Animation Duration
                .build()
        )
    }
}