package com.chatapp.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeFormatter {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun format(timestamp: Long): String {
        return sdf.format(Date(timestamp))
    }

    fun parse(dateString: String): Long? {
        return try {
            sdf.parse(dateString)?.time
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
