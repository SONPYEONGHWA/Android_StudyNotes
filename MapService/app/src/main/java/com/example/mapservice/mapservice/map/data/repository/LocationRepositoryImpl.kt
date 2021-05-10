package com.example.mapservice.mapservice.map.data.repository

import com.example.mapservice.mapservice.map.data.entity.LocationEntity
import com.example.mapservice.mapservice.map.data.source.LocationSource
import io.reactivex.Single
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationSource: LocationSource
): LocationRepository {
    override fun getLocationSearched(query: String): Single<List<LocationEntity>> =
        locationSource.getLocationSearched(query).map { response ->
            response.documents.map { document ->
                document.toLocationEntity()
            }
        }
}