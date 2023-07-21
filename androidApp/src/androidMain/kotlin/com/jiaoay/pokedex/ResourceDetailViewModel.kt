package com.jiaoay.pokedex

import androidx.lifecycle.ViewModel
import com.jiaoay.pokedex.common.api.model.NamedAPIResourceList

class ResourceDetailViewModel : ViewModel() {
    private val repository = PokeApiRepository.instance

    suspend fun getNamedResourceListByUrl(
        urlString: String
    ): NamedAPIResourceList? {
        return repository.getNamedResourceListByUrl(
            urlString = urlString
        )
    }
}