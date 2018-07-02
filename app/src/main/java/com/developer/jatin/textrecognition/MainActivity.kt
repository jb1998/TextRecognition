package com.developer.jatin.textrecognition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.provider.MediaStore
import android.content.Intent
import android.view.View
import com.developer.jatin.textrecognition.R.id.detectBtn







class MainActivity : AppCompatActivity() {
    private val snapBtn: Button? = null
    private val detectBtn: Button? = null
    private val imageView: ImageView? = null
    private val txtView: TextView? = null
    private val imageBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (snapBtn != null) {
            snapBtn.setOnClickListener{
                dispatchTakePictureIntent();
            }
        }
        if (detectBtn != null) {
            detectBtn.setOnClickListener {
                detectTxt();
            }
        }
    }

    private fun detectTxt() {

    }

    val REQUEST_IMAGE_CAPTURE = 1
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
}
