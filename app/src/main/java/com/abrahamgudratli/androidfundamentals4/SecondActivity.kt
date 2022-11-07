package com.abrahamgudratli.androidfundamentals4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        button3.setOnClickListener {
            finish()
        }

        btnUploadPicture.setOnClickListener {
            selectImage()
        }

    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            ivUploadedPicture.setImageURI(uri)
        }
    }


}