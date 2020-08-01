package com.coderyuan.kotlinextensions.io

import androidx.preference.PreferenceManager
import com.coderyuan.kotlinextensions.KotlinExt


private val sp by lazy { PreferenceManager.getDefaultSharedPreferences(KotlinExt.application) }

/**
 * Key值存在于SP中
 *
 * @return Boolean 是否存在key值
 */
fun String?.isInSP(): Boolean {
    return sp.contains(this)
}

/**
 * 保存到SP中
 *
 * 写法示例：("key" to value).saveToSp()
 */
fun Pair<String, Any>.saveToSp() {
    val key = this.first
    val value = this.second
    sp.edit()?.apply {
        when (value) {
            is Boolean -> {
                putBoolean(key, value)
            }
            is String -> {
                putString(key, value)
            }
            is Int -> {
                putInt(key, value)
            }
            is Float -> {
                putFloat(key, value)
            }
            is Long -> {
                putLong(key, value)
            }
        }
    }?.apply()
}

/**
 * 获取Key对应在Sp中的Boolean值
 *
 * @return Boolean
 */
fun String?.spBooleanValue(defaultValue: Boolean): Boolean? {
    return sp?.getBoolean(this, defaultValue)
}

/**
 * 获取Key对应在Sp中的String值
 *
 * @return String
 */
fun String?.spStringValue(defaultValue: String): String? {
    return sp?.getString(this, defaultValue)
}

/**
 * 获取Key对应在Sp中的Int值
 *
 * @return Int
 */
fun String?.spIntValue(defaultValue: Int): Int? {
    return sp?.getInt(this, defaultValue)
}

/**
 * 获取Key对应在Sp中的Float值
 *
 * @return Float
 */
fun String?.spFloatValue(defaultValue: Float): Float? {
    return sp?.getFloat(this, defaultValue)
}

/**
 * 获取Key对应在Sp中的Long值
 *
 * @return Long
 */
fun String?.spLongValue(defaultValue: Long): Long? {
    return sp?.getLong(this, defaultValue)
}

/**
 * 在SP中删除Key值
 */
fun String?.removeInSP() {
    sp?.edit()?.remove(this)?.apply()
}