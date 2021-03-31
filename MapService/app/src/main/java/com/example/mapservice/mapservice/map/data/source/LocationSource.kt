package com.example.mapservice.mapservice.map.data.source

import com.example.mapservice.mapservice.map.model.LocationSearchResponse
import io.reactivex.Single

interface LocationSource {
    fun getLocationSearched(query: String): Single<LocationSearchResponse>
}