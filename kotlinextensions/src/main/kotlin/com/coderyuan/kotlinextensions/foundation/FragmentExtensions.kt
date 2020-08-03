package com.coderyuan.kotlinextensions.foundation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * 添加Fragment
 *
 * @param activity FragmentActivity
 * @param containerId Fragment添加的LayoutId
 * @param executePending 是否先执行挂起的操作
 */
fun Fragment.addTo(
    activity: FragmentActivity,
    containerId: Int = android.R.id.content,
    executePending: Boolean = false
) {
    activity.fragmentAction { transaction, fragmentManager ->
        if (executePending) {
            fragmentManager.executePendingTransactions()
        }
        transaction.add(containerId, this)
    }
}

/**
 * 替换Fragment
 *
 * @param activity FragmentActivity
 * @param containerId Fragment添加的LayoutId
 * @param executePending 是否先执行挂起的操作
 */
fun Fragment.replaceIn(
    activity: FragmentActivity,
    containerId: Int = android.R.id.content,
    executePending: Boolean = false
) {
    activity.fragmentAction { transaction, fragmentManager ->
        if (executePending) {
            fragmentManager.executePendingTransactions()
        }
        transaction.replace(containerId, this)
    }
}

/**
 * 隐藏Fragment
 *
 * @param activity FragmentActivity
 * @param executePending 是否先执行挂起的操作
 */
fun Fragment.hide(
    activity: FragmentActivity,
    executePending: Boolean = false
) {
    activity.fragmentAction { transaction, fragmentManager ->
        if (executePending) {
            fragmentManager.executePendingTransactions()
        }
        transaction.hide(this)
    }
}

/**
 * 移除Fragment
 *
 * @param activity FragmentActivity
 * @param executePending 是否先执行挂起的操作
 */
fun Fragment.remove(
    activity: FragmentActivity,
    executePending: Boolean = false
) {
    activity.fragmentAction { transaction, fragmentManager ->
        if (executePending) {
            fragmentManager.executePendingTransactions()
        }
        transaction.remove(this)
    }
}

/**
 * Fragment操作
 *
 * @private
 * @param action 操作block
 */
private fun FragmentActivity.fragmentAction(action: (transaction: FragmentTransaction, fragmentManager: FragmentManager) -> Unit) {
    val fragmentManager = supportFragmentManager
    try {
        val transaction = fragmentManager.beginTransaction()
        action(transaction, fragmentManager)
        transaction.commitAllowingStateLoss()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}