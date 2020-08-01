package com.coderyuan.kotlinextensions.concurrent

import java.util.concurrent.Executors

private const val TASK_THREAD_NAME = "SerialTaskManager"

/**
 * 默认串行执行队列
 */
private val backgroundSerialExecutor by lazy {
    Executors.newSingleThreadExecutor {
        Thread(TASK_THREAD_NAME)
    }
}

/**
 * 在异步线程串行执行
 */
fun Runnable.runSerial() {
    backgroundSerialExecutor?.execute(this)
}