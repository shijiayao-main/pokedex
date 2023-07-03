package com.jiaoay.pokedex

import android.os.Looper
import com.jiaoay.pokedex.common.api.PokeApi
import com.jiaoay.pokedex.common.api.PokeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject

class PokeApiRepository {
    private val pokeService: PokeApiService = PokeApi()

    suspend fun getApiResource(): JsonObject? {
        try {
            val result = apiCall {
                pokeService.getApiResource()
            }
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private suspend fun <T> apiCall(block: suspend () -> T): T {
        return if (Looper.myLooper() == Looper.getMainLooper()) {
            withContext(Dispatchers.IO) {
                block.invoke()
            }
        } else {
            block.invoke()
        }
    }
}