package com.jiaoay.pokedex.common.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object PokeHttpEngine {
    internal val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    // true:表示打印并生成漂亮的JSON。 默认情况下：false
                    prettyPrint = true
                    // true：删除 JSON 规范限制，允许使用带引号的布尔文本和不带引号的字符串文字
                    isLenient = true
                    // true：表示可以忽略JSON中遇到的未知属性，防止引发序列化异常。 默认情况下：false
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}