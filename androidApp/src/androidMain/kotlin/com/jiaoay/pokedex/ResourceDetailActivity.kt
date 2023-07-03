package com.jiaoay.pokedex

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jiaoay.pokedex.common.theme.PokeDexTheme

class ResourceDetailActivity : ComponentActivity() {

    companion object {
        fun open(activity: ComponentActivity) {
            val intent = Intent(activity, ResourceDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokeDexTheme {

            }
        }
    }
}