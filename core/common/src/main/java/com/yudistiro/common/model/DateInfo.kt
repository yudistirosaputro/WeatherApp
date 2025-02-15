package com.yudistiro.common.model

data class DateInfo(
    val day: Int,
    val monthShort: String,
    val monthFull: String,
    val year: Int,
    val time: String,
    val timeWithAmPm: String
)