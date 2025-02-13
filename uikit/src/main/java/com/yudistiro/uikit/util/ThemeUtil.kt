package com.yudistiro.uikit.util


import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.yudistiro.uikit.model.WeatherCondition

object ThemeUtils {
    fun setTransparentStatusBar(window: Window) {
        window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
            statusBarColor = Color.TRANSPARENT
        }

        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }
    }

    fun Fragment.updateStatusBarColor(color: Int, isLight: Boolean = false) {
        activity?.window?.let { window ->
            window.statusBarColor = color
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = isLight
            }
        }
    }

    fun lightenColor(color: Int, factor: Float): Int {
        val alpha = Color.alpha(color)
        val red = lightenColorComponent(Color.red(color), factor)
        val green = lightenColorComponent(Color.green(color), factor)
        val blue = lightenColorComponent(Color.blue(color), factor)
        return Color.argb(alpha, red, green, blue)
    }

    private fun lightenColorComponent(component: Int, factor: Float): Int {
        return component + ((255 - component) * factor).toInt().coerceIn(0, 255)
    }


    fun isLightColor(color: Int): Boolean {
        val darkness = 1 - (0.299 * Color.red(color) +
                0.587 * Color.green(color) +
                0.114 * Color.blue(color)) / 255
        return darkness < 0.5
    }

    fun getCurrentWeatherCondition(): WeatherCondition {
        return WeatherCondition.SUNNY
    }
}