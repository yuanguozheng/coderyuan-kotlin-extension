package com.coderyuan.kotlinextensions.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * 透明度动画
 */
fun View.alphaAnimation(
    value: Pair<Float, Float>,
    duration: Long = 200,
    interceptor: TimeInterpolator? = LinearInterpolator(),
    finishCallback: ((isCancel: Boolean) -> Unit)? = null
) {
    ObjectAnimator.ofFloat(this, View.ALPHA, value.first, value.second).apply {
        this.duration = duration
        this.interpolator = interceptor
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                finishCallback?.invoke(false)
            }

            override fun onAnimationCancel(p0: Animator?) {
                finishCallback?.invoke(true)
            }

            override fun onAnimationStart(p0: Animator?) {
            }

        })
    }.start()
}

/**
 * X轴位移动画
 */
fun View.translationXAnimation(
    value: Pair<Float, Float>,
    duration: Long = 200,
    interceptor: TimeInterpolator? = LinearInterpolator(),
    finishCallback: ((isCancel: Boolean) -> Unit)? = null
) {
    ObjectAnimator.ofFloat(this, View.TRANSLATION_X, value.first, value.second).apply {
        this.duration = duration
        this.interpolator = interceptor
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                finishCallback?.invoke(false)
            }

            override fun onAnimationCancel(p0: Animator?) {
                finishCallback?.invoke(true)
            }

            override fun onAnimationStart(p0: Animator?) {
            }

        })
    }.start()
}

/**
 * Y轴位移动画
 */
fun View.translationYAnimation(
    value: Pair<Float, Float>,
    duration: Long = 200,
    interceptor: TimeInterpolator? = LinearInterpolator(),
    finishCallback: ((isCancel: Boolean) -> Unit)? = null
) {
    ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, value.first, value.second).apply {
        this.duration = duration
        this.interpolator = interceptor
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                finishCallback?.invoke(false)
            }

            override fun onAnimationCancel(p0: Animator?) {
                finishCallback?.invoke(true)
            }

            override fun onAnimationStart(p0: Animator?) {
            }

        })
    }.start()
}