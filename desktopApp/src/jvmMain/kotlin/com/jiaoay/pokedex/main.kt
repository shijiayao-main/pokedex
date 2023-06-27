package com.jiaoay.pokedex

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jiaoay.pokedex.common.page.PageHome

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        PageHome()
    }
}