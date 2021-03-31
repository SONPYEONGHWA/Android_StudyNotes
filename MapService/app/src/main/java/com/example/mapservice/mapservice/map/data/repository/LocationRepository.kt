package com.example.mapservice.mapservice.map.data.repository

import com.example.mapservice.mapservice.map.model.LocationSearchResponse
import io.reactivex.Single

interface LocationRepository {
    fun getLocationSearched(query: String): Single<LocationSearchResponse>
}