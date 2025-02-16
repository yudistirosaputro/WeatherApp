package com.yudistiro.search

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.yudistiro.common.model.WeatherCondition
import com.yudistiro.di.HomeComponentProvider
import com.yudistiro.di.ViewModelFactory
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.uikit.adapter.SearchResultsAdapter
import com.yudistiro.uikit.util.ThemeUtils
import com.yudistiro.uikit.util.ThemeUtils.lightenColor
import com.yudistiro.uikit.util.ThemeUtils.updateStatusBarColor
import com.yudistiro.weather.feature.home.R
import com.yudistiro.weather.feature.home.databinding.FragmentSearchBinding
import javax.inject.Inject


class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val searchViewModel : SearchViewModel by viewModels {
        viewModelFactory
    }
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteAdapter: SearchResultsAdapter
    private lateinit var searchAdapter: SearchResultsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as HomeComponentProvider)
            .provideHomeComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWeatherUI(binding.clRoot, requireContext())
        searchAdapter = SearchResultsAdapter(
            onFavoriteClick = { result -> /* Handle favorite click */ },
            onItemClick = { result -> navigateToHome(result) }
        )
        favoriteAdapter = SearchResultsAdapter(
            onFavoriteClick = { result -> /* Handle favorite click */ },
            onItemClick = { result -> navigateToHome(result) }
        )
        binding.searchResultsRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(context,2)
        }
        setupSearchView()
        observeData()

    }

    private fun navigateToHome(result: LocationModel) {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToHomeFragment(result))
    }

    private fun observeData() = with(searchViewModel) {
        searchResults.observe(viewLifecycleOwner) {
            when {
                it is DomainResource.Success -> {
                    searchAdapter.submitList(it.data)
                }
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.searchLocations(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: Implement instant search or suggestions
                return true
            }
        })
    }
    private fun setupWeatherUI(layout : ConstraintLayout, context : Context,) {
        // Update status bar color based on weather condition
        when (ThemeUtils.getCurrentWeatherCondition()) {
            WeatherCondition.SUNNY -> updateColorWithWeather(layout, context,
                com.yudistiro.weather.uikit.R.color.sunny_background)
            WeatherCondition.CLOUDS -> updateColorWithWeather(layout, context,
                com.yudistiro.weather.uikit.R.color.cloudy_background)
            WeatherCondition.RAIN -> updateColorWithWeather(layout, context,
                com.yudistiro.weather.uikit.R.color.rainy_background)
            else -> updateColorWithWeather(layout, context, com.yudistiro.weather.uikit.R.color.sunny_background)
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