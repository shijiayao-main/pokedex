package com.jiaoay.pokedex.common.api

import com.jiaoay.pokedex.common.api.model.NamedAPIResourceList
import io.ktor.http.Url
import kotlinx.serialization.json.JsonObject

interface PokeApiService {

    suspend fun getApiResource(): JsonObject

    suspend fun getNamedResourceListByUrl(urlString: String): NamedAPIResourceList
}