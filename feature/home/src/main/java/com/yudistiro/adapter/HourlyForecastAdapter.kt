package com.yudistiro.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yudistiro.common.model.WeatherCondition
import com.yudistiro.domain.model.ForeCastModel
import com.yudistiro.weather.uikit.R as uikit
import com.yudistiro.weather.feature.home.R
import java.text.SimpleDateFormat
import java.util.Locale

class HourlyForecastAdapter : ListAdapter<ForeCastModel, HourlyForecastAdapter.ViewHolder>(DIFF_CALLBACK) {

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


        fun bind(forecast: ForeCastModel) {
            timeText.text = forecast.time
            temperatureText.text = "${forecast.temperature}°C"

            // Set weather icon based on condition
            val iconResource = when (forecast.condition) {
                WeatherCondition.SUNNY -> uikit.drawable.ic_sun
                WeatherCondition.CLOUDS -> uikit.drawable.ic_cloudy
                WeatherCondition.RAIN -> uikit.drawable.ic_rainy
                else -> uikit.drawable.ic_sun
            }
            weatherIcon.setImageResource(iconResource)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ForeCastModel>() {
            override fun areItemsTheSame(oldItem: ForeCastModel, newItem: ForeCastModel): Boolean {
                return oldItem.time == newItem.time
            }

            override fun areContentsTheSame(oldItem: ForeCastModel, newItem: ForeCastModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}