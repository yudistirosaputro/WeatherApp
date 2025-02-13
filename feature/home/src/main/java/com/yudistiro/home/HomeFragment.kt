package com.yudistiro.home

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudistiro.adapter.HourlyForecastAdapter
import com.yudistiro.uikit.model.HourlyForecast
import com.yudistiro.uikit.model.WeatherCondition
import com.yudistiro.uikit.util.ThemeUtils
import com.yudistiro.uikit.util.ThemeUtils.lightenColor
import com.yudistiro.uikit.util.ThemeUtils.updateStatusBarColor
import com.yudistiro.weather.feature.home.R
import com.yudistiro.weather.uikit.R as uikit
import com.yudistiro.weather.feature.home.databinding.FragmentHomeBinding
import java.util.Date

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!
    private lateinit var hourlyForecastAdapter: HourlyForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        setupWeatherUI(binding.clConstraint, requireContext())
        hourlyForecastAdapter = HourlyForecastAdapter()
        binding.hourlyForecastRecyclerView.apply {
            adapter = hourlyForecastAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        loadHourlyForecast()
    }

    private fun loadHourlyForecast() {
        // Example data
        val forecasts = listOf(
            HourlyForecast(
                time = Date(), // Current time
                temperature = 23,
                weatherCondition = WeatherCondition.SUNNY
            ),
            HourlyForecast(
                time = Date(System.currentTimeMillis() + 3600000), // +1 hour
                temperature = 23,
                weatherCondition = WeatherCondition.CLOUDY
            ),
            HourlyForecast(
                time = Date(System.currentTimeMillis() + 3600000), // +1 hour
                temperature = 23,
                weatherCondition = WeatherCondition.RAINY
            ),
            HourlyForecast(
                time = Date(), // Current time
                temperature = 23,
                weatherCondition = WeatherCondition.SUNNY
            ),
            HourlyForecast(
                time = Date(System.currentTimeMillis() + 3600000), // +1 hour
                temperature = 23,
                weatherCondition = WeatherCondition.CLOUDY
            ),
            HourlyForecast(
                time = Date(System.currentTimeMillis() + 3600000), // +1 hour
                temperature = 23,
                weatherCondition = WeatherCondition.RAINY
            ),
            HourlyForecast(
                time = Date(), // Current time
                temperature = 23,
                weatherCondition = WeatherCondition.SUNNY
            ),
            HourlyForecast(
                time = Date(System.currentTimeMillis() + 3600000), // +1 hour
                temperature = 23,
                weatherCondition = WeatherCondition.CLOUDY
            ),
            HourlyForecast(
                time = Date(System.currentTimeMillis() + 3600000), // +1 hour
                temperature = 23,
                weatherCondition = WeatherCondition.RAINY
            ),
            // Add more forecast items...
        )

        hourlyForecastAdapter.submitList(forecasts)
    }
    fun setupWeatherUI(layout : ConstraintLayout, context : Context,) {
        // Update status bar color based on weather condition
        when (ThemeUtils.getCurrentWeatherCondition()) {
            WeatherCondition.SUNNY -> updateColorWithWeather(layout, context,uikit.color.sunny_background)
            WeatherCondition.CLOUDY -> updateColorWithWeather(layout, context,uikit.color.cloudy_background)
            WeatherCondition.RAINY -> updateColorWithWeather(layout, context,uikit.color.rainy_background)
            else -> updateColorWithWeather(layout, context,uikit.color.sunny_background)
        }
    }

    private fun updateColorWithWeather(layout : ConstraintLayout, context : Context, colorRes: Int) {
        val color = ContextCompat.getColor(context, colorRes)
        val gradient = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            orientation = GradientDrawable.Orientation.TL_BR // Top-left to bottom-right
            colors = intArrayOf(color, lightenColor(color, 0.3f))
        }
        layout.background = gradient
        updateStatusBarColor(color, ThemeUtils.isLightColor(color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}