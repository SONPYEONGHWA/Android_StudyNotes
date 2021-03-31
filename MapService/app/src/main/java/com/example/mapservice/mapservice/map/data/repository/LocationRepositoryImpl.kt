package com.example.mapservice.mapservice.map.data.repository

import com.example.mapservice.mapservice.map.data.source.LocationSource
import com.example.mapservice.mapservice.map.model.LocationSearchResponse
import io.reactivex.Single
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationSource: LocationSource
): LocationRepository {
    override fun getLocationSearched(query: String): Single<LocationSearchResponse> =
        locationSource.getLocationSearched(query)
}