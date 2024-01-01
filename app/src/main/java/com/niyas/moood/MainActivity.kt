package com.niyas.moood

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.niyas.moood.data.GetSongsHelper
import com.niyas.moood.ui.theme.MooodTheme
import com.niyas.moood.utils.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var songsHelper: GetSongsHelper

    private val permissionRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it)
            songsHelper.getSongData().forEach { song ->
                Timber.e(song.displayName)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MooodTheme {
                requestAudioPermission()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }

    private fun requestAudioPermission() {
        val permissionManager = PermissionManager(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!permissionManager.checkPermissionHasGranted(Manifest.permission.READ_MEDIA_AUDIO))
                permissionManager.requestPermissionIfNotGranted(
                    Manifest.permission.READ_MEDIA_AUDIO,
                    permissionRequestLauncher
                )
        } else {
            if (!permissionManager.checkPermissionHasGranted(Manifest.permission.READ_EXTERNAL_STORAGE))
                permissionManager.requestPermissionIfNotGranted(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    permissionRequestLauncher
                )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MooodTheme {
        Greeting("Android")
    }
}