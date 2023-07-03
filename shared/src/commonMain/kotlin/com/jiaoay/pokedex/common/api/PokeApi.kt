package com.jiaoay.pokedex.common.api

import com.jiaoay.pokedex.common.api.model.NamedAPIResourceList
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import io.ktor.http.appendEncodedPathSegments
import io.ktor.http.set
import kotlinx.serialization.json.JsonObject

class PokeApi : PokeApiService {
    private val httpClient = PokeHttpEngine.httpClient

    private val scheme = "http"
    private val host = "pokeapi.co"

    private val baseUrlString: String by lazy {
        val urlBuilder = URLBuilder(
            protocol = URLProtocol.HTTP,
            host = host
        ).appendEncodedPathSegments("api/v2")
        urlBuilder.buildString()
    }

    override suspend fun getApiResource(): JsonObject {
        return httpClient
            .get(baseUrlString)
            .body()
    }

    override suspend fun getNamedResourceList(endpoint: String): NamedAPIResourceList {

        val urlString = "$baseUrlString/$endpoint"

        return getNamedResourceListByUrl(url = Url(urlString))
    }

    override suspend fun getNamedResourceListByUrl(url: Url): NamedAPIResourceList {
        return httpClient
            .get(url.toString())
            .body()
    }
}