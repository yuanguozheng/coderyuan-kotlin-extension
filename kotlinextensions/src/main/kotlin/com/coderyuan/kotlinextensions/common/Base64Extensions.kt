package com.coderyuan.kotlinextensions.common

import android.util.Base64

/**
 * 将二进制转换为Base64字符串
 *
 * @param flag 默认Base64.DEFAULT
 * @return Base64
 */
fun ByteArray.toBase64(flag: Int = Base64.DEFAULT): String {
    return Base64.encodeToString(this, flag)
}

/**
 * 将Base64字符串解码为二进制
 *
 * @param flag 默认Base64.DEFAULT
 * @return 二进制
 */
fun String.decodeBase64(flag: Int = Base64.DEFAULT): ByteArray {
    return Base64.decode(this, flag)
}