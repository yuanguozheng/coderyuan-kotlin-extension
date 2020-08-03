package com.coderyuan.kotlinextensions.common

import java.text.SimpleDateFormat
import java.util.*


private const val SECOND = 1 * 1000
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR

private const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
private const val DATE_FORMAT = "yyyy-MM-dd"
private const val DATE_FORMAT_CHINESE = "yyyy年M月d日"
private const val DATE_DOT_FORMAT = "yyyy.MM.dd"
private const val MINUTE_FORMATE = "mm:ss"
private const val MMDD_FORMATE = "MM-dd"

private val DATE_SDF = SimpleDateFormat(DATE_FORMAT, Locale.CHINA)
private val DATE_DOT_SDF = SimpleDateFormat(DATE_DOT_FORMAT, Locale.CHINA)
private val DATE_TIME_SDF = SimpleDateFormat(DATE_TIME_FORMAT, Locale.CHINA)
private val MINUTE_SDF = SimpleDateFormat(MINUTE_FORMATE, Locale.CHINA)
private val MMDD_SDF = SimpleDateFormat(MMDD_FORMATE, Locale.CHINA)
private val MMDD_SDF_CHINESE = SimpleDateFormat(DATE_FORMAT_CHINESE, Locale.CHINA)

fun Long.timestampToDateTimeString(): String = DATE_TIME_SDF.format(Date(this))
fun Long.timestampToDateString(): String = DATE_SDF.format(Date(this))
fun Long.timestampToDateDotString(): String = DATE_DOT_SDF.format(Date(this))
fun Long.timestampToMinuteString(): String = MINUTE_SDF.format(Date(this))
fun Long.timestampToMMDDString(): String = MMDD_SDF.format(Date(this))
fun Long.timestampToMMDDStringChinese(): String = MMDD_SDF_CHINESE.format(Date(this))
fun Long.timestampToCustomFormatString(format: String): String =
    SimpleDateFormat(format, Locale.CHINA).format(
        Date(this)
    )


fun Long.timestampToReadableTimeString(): String {
    val current = System.currentTimeMillis()
    val duration = current - this
    when {
        duration < HOUR -> {
            return "刚刚"
        }
        duration < DAY -> {
            return "${duration / HOUR}小时前"
        }
        duration < (3 * DAY) -> {
            return "${duration / DAY}天前"
        }
        else -> {
            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            calendar.time = Date(this)
            val targetYear = calendar.get(Calendar.YEAR)
            return if (currentYear == targetYear) {
                this.timestampToMMDDString()
            } else {
                this.timestampToDateString()
            }
        }
    }
}