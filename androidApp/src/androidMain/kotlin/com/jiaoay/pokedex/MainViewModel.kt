package com.jiaoay.pokedex

import androidx.lifecycle.ViewModel
import kotlinx.serialization.json.JsonPrimitive

class MainViewModel: ViewModel() {

    private val repository = PokeApiRepository.instance

    suspend fun getApiResource(): Map<String, String> {
        val resourceJson = repository.getApiResource()

        val resultMap: MutableMap<String, String> = HashMap()

        resourceJson?.forEach {
            val key = it.key
            val value = it.value
            if (value is JsonPrimitive) {
                resultMap[key] = value.content
            }
        }

        return resultMap
    }
}