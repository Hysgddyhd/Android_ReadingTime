package com.wuyuntian.a197547_readingtime

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.wuyuntian.a197547_readingtime.room.Plan
import com.wuyuntian.a197547_readingtime.ui.theme.ReadingTimeTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val book_title=sharedPref.getString(getString(R.string.book_title), resources.getString(R.string.plan_id))
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
               // performAction(...)
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.POST_NOTIFICATIONS) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                //showInContextUI(...)
            }
            else -> {
                // You can directly ask for the permission.

            }
        }
        Log.d("sharedPref", "book title: "+book_title)
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onStart")
        enableEdgeToEdge()
        setContent {
            ReadingTimeTheme {
                ReadingTimeApp(sharedPref)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestory")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart")
    }
}


