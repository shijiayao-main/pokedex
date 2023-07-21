package com.jiaoay.pokedex

import android.os.Looper
import com.jiaoay.pokedex.common.api.PokeApi
import com.jiaoay.pokedex.common.api.PokeApiService
import com.jiaoay.pokedex.common.api.model.NamedAPIResourceList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject

class PokeApiRepository private constructor() {

    companion object {
        val instance: PokeApiRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PokeApiRepository()
        }
    }

    private val pokeService: PokeApiService = PokeApi()

    suspend fun getApiResource(): JsonObject? {
        return try {
            val result = apiCall {
                pokeService.getApiResource()
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getNamedResourceListByUrl(urlString: String): NamedAPIResourceList? {
        return try {
            apiCall {
                pokeService.getNamedResourceListByUrl(urlString = urlString)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
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