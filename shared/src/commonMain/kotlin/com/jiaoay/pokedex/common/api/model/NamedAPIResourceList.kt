package com.jiaoay.pokedex.common.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamedAPIResourceList(
    @SerialName("count")
    val count: Int? = 0,
    @SerialName("next")
    val next: String? = "",
    @SerialName("previous")
    val previous: String? = "",
    @SerialName("results")
    val results: List<NamedAPIResource>? = null
)

@Serializable
data class NamedAPIResource(
    @SerialName("name")
    val name: String? = "",
    @SerialName("url")
    val url: String? = ""
)