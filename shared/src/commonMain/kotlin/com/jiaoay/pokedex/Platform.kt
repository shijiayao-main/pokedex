package com.jiaoay.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform