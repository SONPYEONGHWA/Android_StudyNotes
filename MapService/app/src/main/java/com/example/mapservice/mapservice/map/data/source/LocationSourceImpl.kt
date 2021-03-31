package com.example.mapservice.mapservice.map.data.source

import com.example.mapservice.mapservice.map.model.LocationSearchResponse
import io.reactivex.Single
import javax.inject.Inject

class LocationSourceImpl @Inject constructor(
    private val searchLocationService: SearchLocationService
): LocationSource {
    override fun getLocationSearched(query: String): Single<LocationSearchResponse> =
        searchLocationService.getLocationSearched(query)
}