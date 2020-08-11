package com.coderyuan.kotlinextensions.security

import android.util.Base64
import com.coderyuan.kotlinextensions.common.decodeBase64
import com.coderyuan.kotlinextensions.common.toBase64
import java.nio.charset.Charset
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

private const val RSA_NAME = "RSA"

/**
 * 将Base64字符串转换为RSA公钥
 *
 * @param base64DecodeFlag Base解码选项，默认为：Base64.DEFAULT
 * @return RSA公钥
 */
fun String.toRSAPublicKey(base64DecodeFlag: Int = Base64.DEFAULT): RSAPublicKey? {
    return getPublicKey(this, base64DecodeFlag)
}

/**
 * 将Base64字符串转换为RSA私钥
 *
 * @param base64DecodeFlag Base解码选项，默认为：Base64.DEFAULT
 * @return RSA私钥
 */
fun String.toRSAPrivateKey(base64DecodeFlag: Int = Base64.DEFAULT): RSAPrivateKey? {
    return getPrivateKey(this, base64DecodeFlag)
}

/**
 * 将字符串使用RSA公钥加密，并返回Base64字符串密文
 *
 * @param key RSA公钥
 * @param base64DecodeFlag Base编码选项，默认为：Base64.DEFAULT
 * @return 密文
 */
fun String.encryptByRSAPubKey(key: RSAPublicKey, base64DecodeFlag: Int = Base64.DEFAULT): String? {
    return encryptByPublicKey(this.toByteArray(Charset.defaultCharset()), key)?.toBase64(base64DecodeFlag)
}

/**
 * 将Base64字符串密文使用RSA私钥解密
 *
 * @param key RSA公钥
 * @param base64DecodeFlag Base64解码选项，默认为：Base64.DEFAULT
 * @return 明文
 */
fun String.decryptByRSAPriKey(key: RSAPrivateKey, base64DecodeFlag: Int = Base64.DEFAULT): String? {
    val bytes = decryptByPrivateKey(this.decodeBase64(base64DecodeFlag), key) ?: return null
    return String(bytes)
}

private fun getPublicKey(publicKey: String, flag: Int = Base64.DEFAULT): RSAPublicKey? {
    var key: RSAPublicKey? = null
    try {
        val keyBytes: ByteArray = Base64.decode(publicKey, flag)
        val spec = X509EncodedKeySpec(keyBytes)
        val keyFactory: KeyFactory = KeyFactory.getInstance(RSA_NAME)
        key = keyFactory.generatePublic(spec) as RSAPublicKey
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return key
}

private fun getPrivateKey(privateKey: String, flag: Int = Base64.DEFAULT): RSAPrivateKey? {
    var key: RSAPrivateKey? = null
    try {
        val keyBytes: ByteArray = Base64.decode(privateKey, flag)
        val spec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory: KeyFactory = KeyFactory.getInstance(RSA_NAME)
        key = keyFactory.generatePrivate(spec) as RSAPrivateKey
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return key
}

/**
 * 公钥加密
 *
 * @param data 需要加密的二进制数据
 * @param publicKey RSA公钥
 * @return 加密后的二进制数据
 */
private fun encryptByPublicKey(
    data: ByteArray,
    publicKey: RSAPublicKey?
): ByteArray? {
    return try {
        val cipher: Cipher = Cipher.getInstance(RSA_NAME)
        // 编码前设定编码方式及密钥
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        // 传入编码数据并返回编码结果
        return cipher.doFinal(data)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

/**
 * 私钥解密
 *
 * @param bytes 需要解密的二进制数据
 * @param privateKey RSA私钥
 * @return 解密后的二进制数据
 */
private fun decryptByPrivateKey(
    bytes: ByteArray?,
    privateKey: RSAPrivateKey?
): ByteArray? {
    return try {
        val cipher: Cipher = Cipher.getInstance(RSA_NAME)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(bytes)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}