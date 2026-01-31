package com.aizarath.spool.feature_note.domain.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateFormatter {
    private val zoneId = ZoneId.systemDefault()
    private val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy").withZone(zoneId)

    operator fun invoke(timestamp: Long): String {
        val instant = Instant.ofEpochMilli(timestamp)
        return formatter.format(instant)
    }
}