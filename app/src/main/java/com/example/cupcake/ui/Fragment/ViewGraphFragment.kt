package com.example.cupcake.ui.Fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cupcake.R
import com.example.cupcake.Utils.RoundedBarChartRenderer
import com.example.cupcake.databinding.FragmentViewGraphBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.Utils

class ViewGraphFragment : Fragment() {
    private var _binding:FragmentViewGraphBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle? ,
    ) : View? {
        _binding=FragmentViewGraphBinding.inflate(inflater,container,false)

        binding.apply {

            setBarChart()
            setPieChart()

        }

        return binding.root
    }

    private fun setBarChart() {
        // Create a list of colors to represent the color of each bar
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.RED)
        colors.add(Color.BLUE)
        colors.add(Color.GREEN)


        //Bar Values
        val entries: MutableList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, 15f))
        entries.add(BarEntry(1f, 20f))
        entries.add(BarEntry(2f, 30f))


        val dataSet = BarDataSet(entries, "bar chart")
        dataSet.apply {
            this.colors = colors
            valueTextColor = Color.BLACK
            valueTextSize = 11f
            setDrawValues(true)
            // set custom value formatter that returns labels to display on top of bars
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "${value.toInt()} SAR"
                }
            }
        }


        // Create custom renderer with 4dp corner radius
        val roundedRenderer = RoundedBarChartRenderer(
            binding.idBarChart,
            binding.idBarChart.animator,
            binding.idBarChart.viewPortHandler,
            Utils.convertDpToPixel(4f)
        )


        val barData = BarData(dataSet)






        binding.idBarChart.apply {

            renderer = roundedRenderer
            data = barData

            setDrawValueAboveBar(true)
            xAxis.setDrawGridLines(false)
            setTouchEnabled(false)
            axisRight.setDrawLabels(false)
            axisLeft.setDrawLabels(false)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            setDrawBorders(false)
            description.isEnabled = false
            legend.isEnabled = false
            barData.setDrawValues(true)
        }


        val labels = arrayOf("One","Six","Twelve")

        val xAxis = binding.idBarChart.xAxis


        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
//            setCenterAxisLabels(true)
            xAxis.mLabelWidth = barData.barWidth.toInt() // set the bar width as label width

            granularity = 1f
//            isGranularityEnabled = false
            labelCount = labels.size

            valueFormatter = IndexAxisValueFormatter(labels)
            setAvoidFirstLastClipping(false)
//
//            xAxis.valueFormatter = object : ValueFormatter() {
//                override fun getFormattedValue(value: Float): String {
//                    // Adjust the logic to return the appropriate label based on the value
//                    // For example, if your labels are strings in an array, you can return the corresponding label based on the value index.
//                    // Make sure to handle edge cases appropriately to avoid index out of bounds errors.
//                    val labels = arrayOf("Label1", "Label2", "Label3", "Label4", "Label5")
//                    val index = value.toInt()
//                    return if (index >= 0 && index < labels.size) {
//                        labels[index]
//                    } else ""
//                }
//            }

//            xAxis.valueFormatter = object : ValueFormatter() {
//                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//                    // return the label text
//                   return labels[0]// generate label text based on value
////                    val maxLabelWidth = axis?.labelWidth ?: 0f // get the max label width
////                    val labelWidth = mAxisLabelPaint.measureText(label) // get the label width
////                    if (labelWidth < maxLabelWidth) {
////                        // calculate the padding to center the label
////                        val padding = (maxLabelWidth - labelWidth) / 2f
////                        axis?.labelPadding = padding.toInt()
//                }
//            }
            textColor = Color.BLACK
            textSize = 11f
//            typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/roboto_medium.ttf")

        }


//        val centeredRenderer = CenteredXAxisRenderer(
//            binding.idBarChart.viewPortHandler,
//            binding.idBarChart.xAxis,
//            binding.idBarChart.getTransformer(YAxis.AxisDependency.LEFT),
//            barData.barWidth
//        )
//        binding.idBarChart.setXAxisRenderer(centeredRenderer)

        binding.idBarChart.invalidate()

    }

    private fun setPieChart() {
        binding.pieChart.apply {
            this.setTouchEnabled(false)
            // Disable the description label
            description.isEnabled = false
            //Data for Pie Chart
            var entries = ArrayList<PieEntry>()
            entries.add(PieEntry(25f, ""))
            entries.add(PieEntry(40f, ""))
            entries.add(PieEntry(60f, ""))
            var dataSet = PieDataSet(entries, "")
            // Define custom colors for the Pie Chart
            val colors = mutableListOf<Int>()
            colors.add(ColorTemplate.rgb("#2AD5CD"))
            colors.add(ColorTemplate.rgb("#008000"))
            colors.add(ColorTemplate.rgb("#BAEDF9"))
            dataSet.colors = colors
//            dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
            var data = PieData(dataSet)

            setData(data)

// Customize the chart
            centerText = ""
//            setCenterTextSize(18f)


            //Animate on start
            animateY(1500, Easing.EaseInOutQuad)


// Show the labels on the right side

            dataSet.apply {
                setDrawValues(true)
                setDrawIcons(false)
                sliceSpace = 1f
                selectionShift = 5f
                //value line customization
                valueLinePart1OffsetPercentage = 90f
                valueLinePart2Length = 0.6f
                valueLineColor = android.R.color.transparent
                //value outside the chart
                yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                //disable labels
                setDrawEntryLabels(false)
                //Value Text Customization
                valueTextColor = Color.BLACK
                valueTextSize = 12f
//                valueTypeface =
//                    Typeface.createFromAsset(requireActivity().assets, "fonts/roboto.ttf")

                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return value.toInt().toString()
                    }
                }
            }


            //Disable center hole
            isDrawHoleEnabled = false
            //customize label text
            //            setDrawEntryLabels(false)
//            setEntryLabelColor(Color.BLACK)
//            setEntryLabelTextSize(12f)
//            setEntryLabelTypeface(Typeface.DEFAULT_BOLD)


            // Customize the legend
            legend.apply {
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                verticalAlignment = Legend.LegendVerticalAlignment.CENTER
                orientation = Legend.LegendOrientation.VERTICAL
                setDrawInside(false)
                yEntrySpace = 10f
                yOffset = -15f
//                xOffset = 10f
                textSize = 12f
                textColor = Color.BLACK
//                typeface =
//                    Typeface.createFromAsset(requireActivity().assets, "fonts/roboto.ttf")
                formSize = 15f

            }
        }
    }


}