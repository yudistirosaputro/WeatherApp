package com.yudistiro.common.util

import com.yudistiro.common.model.DateInfo
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class DateTimeUtils {
    private val defaultFormat = "yyyy-MM-dd HH:mm:ss"
    fun getCurrentDateInfo(): DateInfo {
        val now = LocalDateTime.now()
        return DateInfo(
            day = now.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm")),
            year = now.year,
            time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
            timeWithAmPm = now.format(DateTimeFormatter.ofPattern("hh:mm a"))
        )
    }

    fun convertToHoursOnly(dateTimeString: String?): String {
        if (dateTimeString.isNullOrBlank()) return EMPTY_STRING

        return try {
            val inputFormat = SimpleDateFormat(defaultFormat, Locale.getDefault())
            val outputFormat = SimpleDateFormat("HH:00", Locale.getDefault())

            val date = inputFormat.parse(dateTimeString)
            date?.let { outputFormat.format(it) } ?: EMPTY_STRING
        } catch (e: Exception) {
            EMPTY_STRING
        }
    }

    fun isValidForecastTime(forecastDateTimeString: String): Boolean {
        return try {
            val inputFormat = SimpleDateFormat(defaultFormat, Locale.ENGLISH)
            val forecastDate = inputFormat.parse(forecastDateTimeString)

            val currentDateTime = Calendar.getInstance().time

            forecastDate?.after(currentDateTime) ?: false
        } catch (e: Exception) {
            false
        }
    }
    }