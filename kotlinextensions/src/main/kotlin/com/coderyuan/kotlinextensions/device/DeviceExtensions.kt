package com.coderyuan.kotlinextensions.device

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * 获取屏幕高度
 */
//inline val Context?.screenHeight: Int
//    get() {
//        this ?: return 0
//        return getScreenHeightInner(this)
//    }

internal fun getScreenHeightInner(ctx: Context): Int {
    val windowMgr = (ctx.getSystemService(Context.WINDOW_SERVICE) as? WindowManager) ?: return 0
    return DisplayMetrics().apply {
        windowMgr.defaultDisplay?.getRealMetrics(this)
    }.heightPixels
}