package com.jiaoay.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiaoay.pokedex.common.theme.PokeDexTheme
import com.jiaoay.pokedex.common.widget.SimpleDoubleLineItem
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDexTheme {
                PageHome()
            }
        }
    }
}

@Composable
fun ComponentActivity.PageHome() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val viewModel: MainViewModel by viewModels<MainViewModel>()
        val scope = rememberCoroutineScope()
        var map: Map<String, String> by remember {
            mutableStateOf(emptyMap())
        }
        if (map.isEmpty()) {
            LaunchedEffect(true) {
                scope.launch {
                    map = viewModel.getApiResource()
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "别急，让子弹飞一会",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            }

        } else {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                map.forEach {
                    val key = it.key
                    val value = it.value
                    item {
                        SimpleDoubleLineItem(
                            firstLine = value,
                            secondLine = key
                        ) {
                            ResourceDetailActivity.open(this@PageHome)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    SimpleDoubleLineItem(
        firstLine = "first",
        secondLine = "second"
    ) {}
}
