package com.jiaoay.pokedex

class DesktopPlatform : Platform {
    override val name: String = "Desktop: 还不知道怎么获取"
}

actual fun getPlatform(): Platform = DesktopPlatform()
