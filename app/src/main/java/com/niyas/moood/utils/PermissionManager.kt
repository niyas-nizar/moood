package com.niyas.moood.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class PermissionManager(private val context: Context) {

    companion object {
        private const val REQUEST_CODE = 123
    }

    fun checkPermissionHasGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissionIfNotGranted(
        permission: String,
        permissionRequestLauncher: ActivityResultLauncher<String>
    ) {
        permissionRequestLauncher.launch(permission)
    }
}
