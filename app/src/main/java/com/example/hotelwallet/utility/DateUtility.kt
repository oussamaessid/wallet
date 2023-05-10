package com.example.hotelwallet.utility

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


fun String.getMonthAndYearFromDate(): String {
    val dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val date1: OffsetDateTime = OffsetDateTime.parse(this, dateTimeFormatter)
    val fmt = DateTimeFormatter.ofPattern("MMMM yyyy")
    return "${fmt.format(date1)}"
}