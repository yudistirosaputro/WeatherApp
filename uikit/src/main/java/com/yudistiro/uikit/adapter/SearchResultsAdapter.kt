package com.yudistiro.uikit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.weather.uikit.databinding.ItemSearchLocationBinding
import com.yudistiro.weather.uikit.R

class SearchResultsAdapter(
    private val onFavoriteClick: (LocationModel) -> Unit,
    private val onItemClick: (LocationModel) -> Unit
) : ListAdapter<LocationModel, SearchResultsAdapter.SearchResultViewHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemSearchLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchResultViewHolder(binding, onFavoriteClick,onItemClick)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchResultViewHolder(
        private val binding: ItemSearchLocationBinding,
        private val onFavoriteClick: (LocationModel) -> Unit,
        private val onItemClick: (LocationModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(location: LocationModel) {
            binding.apply {
                locationName.text = "${location.cityName } ${location.country}"
                root.setOnClickListener {
                    onItemClick(location)
                }
                favoriteIcon.setOnClickListener {
                    onFavoriteClick(location)
                }
                favoriteIcon.setImageResource(
                    if (location.isFavorite) R.drawable.ic_favorite_filled
                    else R.drawable.ic_favorite_line
                )
            }
        }
    }
}

class LocationDiffCallback : DiffUtil.ItemCallback<LocationModel>() {
    override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
        return oldItem.latitude == newItem.latitude &&
                oldItem.longitude == newItem.longitude
    }

    override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
        return oldItem == newItem
    }
}