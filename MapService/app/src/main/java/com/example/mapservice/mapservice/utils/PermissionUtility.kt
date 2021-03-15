package com.example.mapservice.mapservice.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.mapservice.mapservice.MainActivity

object PermissionUtility {
    val REQUIRED_PERMISSIONS = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    fun checkLocationPermissions(context: Context): Boolean {
        var hasFineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            REQUIRED_PERMISSIONS[0]
        )
        var hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            REQUIRED_PERMISSIONS[1]
        )

        return (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED
                && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED)
    }
}