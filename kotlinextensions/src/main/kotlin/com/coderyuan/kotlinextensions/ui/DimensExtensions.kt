package com.coderyuan.kotlinextensions.ui

import com.coderyuan.kotlinextensions.KotlinExt

/**
 * 屏幕像素密度
 */
inline val density
    get() = KotlinExt.application?.resources?.displayMetrics?.density ?: 0f

/**
 * dp转px
 */
fun Int.dp2px(): Int {
    return (this * density).toInt()
}

/**
 * dp转px
 */
fun Float.dp2px(): Float {
    return (this * density)
}

/**
 * px转dp
 */
fun Int.px2dp(): Int {
    return ((this / density).toInt())
}

/**
 * px转dp
 */
fun Float.px2dp(): Float {
    return this / density
}
