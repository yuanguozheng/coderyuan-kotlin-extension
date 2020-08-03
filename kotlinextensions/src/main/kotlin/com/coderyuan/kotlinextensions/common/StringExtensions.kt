package com.coderyuan.kotlinextensions.common

import android.content.ClipData
import android.content.ClipboardManager
import com.coderyuan.kotlinextensions.KotlinExt
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.regex.Pattern

/**
 * 复制到剪贴板
 */
fun String.copyToClipboard() {
    val manager = KotlinExt.application?.getSystemService(ClipboardManager::class.java)
    val clipData: ClipData = ClipData.newPlainText("text", this)
    manager?.setPrimaryClip(clipData)
}

/**
 * 删除HTML标签
 */
fun String.delHTMLTags(): String {
    var htmlStr = this
    try {
        val regExScript = "<script[^>]*?>[\\s\\S]*?</script>" // 定义script的正则表达式
        val regExStyle = "<style[^>]*?>[\\s\\S]*?</style>" // 定义style的正则表达式
        val regExHtml = "<[^>]+>" // 定义HTML标签的正则表达式

        val pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE)
        val mScript = pScript.matcher(htmlStr)
        htmlStr = mScript.replaceAll("") // 过滤script标签

        val pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE)
        val mStyle = pStyle.matcher(htmlStr)
        htmlStr = mStyle.replaceAll("") // 过滤style标签

        val pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE)
        val mHtml = pHtml.matcher(htmlStr)
        htmlStr = mHtml.replaceAll("") // 过滤html标签
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return htmlStr.trim() // 返回文本字符串
}

/**
 * URLEncode
 */
fun String?.urlEncode(charset: String = "utf-8"): String? {
    return try {
        URLEncoder.encode(this, charset)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}

/**
 * URLDecode
 */
fun String?.urlDecode(charset: String = "utf-8"): String? {
    return try {
        URLDecoder.decode(this, charset)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}