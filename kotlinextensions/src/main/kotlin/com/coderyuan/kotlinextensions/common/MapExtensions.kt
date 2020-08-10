package com.coderyuan.kotlinextensions.common

/**
 * 将map转换为URL参数字符串
 *
 * @param urlEncode 是否使用URLEncode，默认true
 */
fun Map<String, Any>.toUrlParams(urlEncode: Boolean = true, charset: String = "utf-8"): String {
    val builder = StringBuilder()
    this.keys.forEach {
        val value = this[it]
        if (urlEncode) {
            builder.append("${it.urlEncode(charset)}=${value.toString().urlEncode(charset)}")
        } else {
            builder.append("${it}=${value.toString()}")
        }
        builder.append("&")
    }
    builder.deleteCharAt(builder.lastIndex)
    return builder.toString()
}
