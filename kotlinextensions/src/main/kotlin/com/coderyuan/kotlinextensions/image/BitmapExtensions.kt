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
    val newBM: Bitmap? = createNewBitmapByMatrix(this, matrix)
    if (!this.isRecycled && autoRecycleOrigin) {
        this.recycle()
    }
    return newBM
}

/**
 * 旋转Bitmap
 *
 * @param degree 角度
 * @param autoRecycleOrigin 是否自动回收原图
 */
fun Bitmap.rotate(degree: Int, autoRecycleOrigin: Boolean = true): Bitmap? {
    val matrix = Matrix()
    matrix.postRotate(degree.toFloat())
    val newBM: Bitmap? = createNewBitmapByMatrix(this, matrix)
    if (!this.isRecycled && autoRecycleOrigin) {
        this.recycle()
    }
    return newBM
}

/**
 * 使用Matrix创建一个新的Bitmap
 *
 * @param bitmap 需要处理的Bitmap
 * @param matrix 目标Matrix
 */
private fun createNewBitmapByMatrix(bitmap: Bitmap, matrix: Matrix): Bitmap? {
    var newBM: Bitmap? = null
    val height = bitmap.height
    val width = bitmap.width
    try {
        newBM = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return newBM
}