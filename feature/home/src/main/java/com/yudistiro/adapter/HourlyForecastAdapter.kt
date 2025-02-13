package com.yudistiro.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yudistiro.uikit.model.HourlyForecast
import com.yudistiro.uikit.model.WeatherCondition
import com.yudistiro.weather.uikit.R as uikit
import com.yudistiro.weather.feature.home.R
import java.text.SimpleDateFormat
import java.util.Locale

class HourlyForecastAdapter : ListAdapter<HourlyForecast, HourlyForecastAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeText: TextView = itemView.findViewById(R.id.timeText)
        private val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIcon)
        private val temperatureText: TextView = itemView.findViewById(R.id.temperatureText)

        private val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

        fun bind(forecast: HourlyForecast) {
            timeText.text = timeFormatter.format(forecast.time)
            temperatureText.text = "${forecast.temperature}°C"

            // Set weather icon based on condition
            val iconResource = when (forecast.weatherCondition) {
                WeatherCondition.SUNNY -> uikit.drawable.ic_sun
                WeatherCondition.CLOUDY -> uikit.drawable.ic_cloudy
                WeatherCondition.RAINY -> uikit.drawable.ic_rainy
                else -> uikit.drawable.ic_sun
            }
            weatherIcon.setImageResource(iconResource)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HourlyForecast>() {
            override fun areItemsTheSame(oldItem: HourlyForecast, newItem: HourlyForecast): Boolean {
                return oldItem.time == newItem.time
            }

            override fun areContentsTheSame(oldItem: HourlyForecast, newItem: HourlyForecast): Boolean {
                return oldItem == newItem
            }
        }
    }
}