package com.coderyuan.kotlinextensions

import android.app.Application

class KotlinExt {

    companion object {

        var application: Application? = null
            private set

        /**
         * 初始化Kotlin扩展，因部分功能需要使用Context，所以需要在这里初始化
         */
        @JvmStatic
        fun init(app: Application) {
            application = app
        }
    }
}