package com.coderyuan.kotlinextensions.image

import android.graphics.Bitmap
import android.graphics.Matrix

/**
 * 缩放Bitmap
 *
 * @param newMaxSize 最长尺寸
 * @param autoRecycleOrigin 是否自动回收原图
 */
fun Bitmap.scale(newMaxSize: Int, autoRecycleOrigin: Boolean = true): Bitmap? {
    val height = this.height
    val width = this.width
    val rate = if (height > width) {
        newMaxSize / height.toFloat()
    } else {
        newMaxSize / width.toFloat()
    }
    val matrix = Matrix()
    matrix.postScale(rate, rate)
    var newBM: Bitmap? = null
    try {
        newBM = Bitmap.createBitmap(this, 0, 0, width, height, matrix, false)
        if (!this.isRecycled && autoRecycleOrigin) {
            this.recycle()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return newBM
}