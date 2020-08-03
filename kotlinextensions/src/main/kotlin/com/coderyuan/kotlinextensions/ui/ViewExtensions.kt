package com.coderyuan.kotlinextensions.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup

/**
 * 设置/获取View的marginStart
 */
inline var View.marginStart: Int
    get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.marginStart ?: 0
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.marginStart = value
    }

/**
 * 设置/获取View的marginTop
 */
inline var View.marginTop: Int
    get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: 0
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin = value
    }

/**
 * 设置/获取View的marginEnd
 */
inline var View.marginEnd: Int
    get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.marginEnd ?: 0
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.marginEnd = value
    }

/**
 * 设置/获取View的marginBottom
 */
inline var View.marginBottom: Int
    get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin ?: 0
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin = value
    }

/**
 * 设置/获取View的paddingStart
 */
inline var View.startPadding: Int
    get() = this.paddingStart
    set(value) {
        val top = this.paddingTop
        val end = this.paddingEnd
        val bottom = this.paddingBottom
        this.setPadding(value, top, end, bottom)
    }

/**
 * 设置/获取View的paddingTop
 */
inline var View.topPadding: Int
    get() = this.paddingTop
    set(value) {
        val start = this.paddingStart
        val end = this.paddingEnd
        val bottom = this.paddingBottom
        this.setPadding(start, value, end, bottom)
    }

/**
 * 设置/获取View的paddingEnd
 */
inline var View.endPadding: Int
    get() = this.paddingEnd
    set(value) {
        val start = this.paddingStart
        val top = this.paddingTop
        val bottom = this.paddingBottom
        this.setPadding(start, top, value, bottom)
    }

/**
 * 设置/获取View的paddingBottom
 */
inline var View.bottomPadding: Int
    get() = this.paddingBottom
    set(value) {
        val start = this.paddingStart
        val top = this.paddingTop
        val end = this.paddingEnd
        this.setPadding(start, top, end, value)
    }

/**
 * 从某个View截取Bitmap
 *
 * @param config Bitmap配置，RGB565、ARGB8888
 */
fun View?.captureImage(config: Bitmap.Config = Bitmap.Config.RGB_565): Bitmap? {
    this ?: return null
    return if (measuredWidth > 0 && measuredHeight > 0) {
        try {
            val capture = Bitmap.createBitmap(measuredWidth, measuredHeight, config)
            Canvas(capture).let {
                background?.draw(it)
                val state = it.save()
                draw(it)
                it.restoreToCount(state)
            }
            capture
        } catch (e: OutOfMemoryError) {
            null
        } catch (e: Exception) {
            null
        }
    } else {
        null
    }
}
