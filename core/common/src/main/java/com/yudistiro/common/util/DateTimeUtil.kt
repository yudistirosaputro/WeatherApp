package com.yudistiro.common.util

import com.yudistiro.common.model.DateInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateTimeUtils {
    // Get Current Date Components
    fun getCurrentDateInfo(): DateInfo {
        val now = LocalDateTime.now()
        return DateInfo(
            day = now.dayOfMonth,
            monthShort = now.month.toString().take(3).capitalize(),
            monthFull = now.month.toString().capitalize(),
            year = now.year,
            time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
            timeWithAmPm = now.format(DateTimeFormatter.ofPattern("hh:mm a"))
        )
    }

    fun getDateInTimezone(zoneId: String): ZonedDateTime {
        return ZonedDateTime.now(ZoneId.of(zoneId))
    }

    fun formatDate(
        pattern: String = "dd/MM/yyyy",
        locale: Locale = Locale.getDefault()
    ): String {
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)
        return LocalDate.now().format(formatter)
    }
}