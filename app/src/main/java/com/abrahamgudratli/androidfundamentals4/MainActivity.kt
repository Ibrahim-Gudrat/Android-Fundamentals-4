package com.abrahamgudratli.androidfundamentals4

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custon_toast.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Toast(this).apply {
                duration = Toast.LENGTH_LONG
                view = layoutInflater.inflate(R.layout.custon_toast, clCustomToast)
                show()
            }
        }

        button2.setOnClickListener {
            Intent(this, SecondActivity::class.java).also {
                startActivity(it)
            }
        }

        button4.setOnClickListener {
            requestPermission()
        }

    }

    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    private fun hasLocationBackgroundPermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        var list = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission())
            list.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (!hasLocationForegroundPermission())
            list.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)

        if (!hasLocationBackgroundPermission())
            list.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        if (list.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, list.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Permission Request Res", "${grantResults[i]} is granted")
                }
            }
        }
    }



}