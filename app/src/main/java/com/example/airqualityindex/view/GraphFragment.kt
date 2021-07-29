package com.example.airqualityindex.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.broooapps.graphview.CurveGraphConfig
import com.broooapps.graphview.models.GraphData
import com.broooapps.graphview.models.PointMap
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

        val cityName = GraphFragmentArgs.fromBundle(requireArguments()).cityName
        val aqiLevel = GraphFragmentArgs.fromBundle(requireArguments()).aqiLevel

        val number = aqiLevel.toIntOrNull()

        val pointMap = PointMap()
        number?.let {
            pointMap.addPoint(0, it)
        }


        val p2 = PointMap()
        if (number != null) {
            p2.addPoint(0, 250)
        }

        graphBinding.curveGraphView.configure(
            CurveGraphConfig.Builder(requireContext())
                .setAxisColor(R.color.Blue)
                .setVerticalGuideline(4)
                .setHorizontalGuideline(2)
                .setGuidelineColor(R.color.Red)
                .setxAxisScaleTextColor(R.color.Black)
                .setyAxisScaleTextColor(R.color.Black)
                .setAnimationDuration(1000)
                .build()
        )
        val gd = GraphData.builder(requireContext())
            .setPointMap(pointMap)
            .setGraphStroke(R.color.Black)
            .setGraphGradient(R.color.BlueViolet, R.color.RoyalBlue)
            .setStraightLine(true)
            .setPointRadius(10)
            .setPointColor(R.color.Red)
            .animateLine(true)
            .build()

        val gd2 = GraphData.builder(requireContext())
            .setPointMap(p2)
            .setGraphStroke(R.color.Green)
            .setGraphGradient(R.color.gradientStartColor, R.color.gradientEndColor)
            .build()


        Handler().postDelayed(
            Runnable { graphBinding.curveGraphView.setData(2, 1000, gd, gd2) },
            150
        )
    }
}