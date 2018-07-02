package com.developer.jatin.textrecognition

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.provider.MediaStore
import android.content.Intent
import android.view.View
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.android.gms.tasks.OnSuccessListener
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    private val snapBtn: Button? = null
    private val detectBtn: Button? = null
    private val imageView: ImageView? = null
    private val txtView: TextView? = null
    private var imageBitmap: Bitmap? = null
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
        val image = FirebaseVisionImage.fromBitmap(imageBitmap!!)
        val detector = FirebaseVision.getInstance().visionTextDetector
        detector.detectInImage(image).addOnSuccessListener { firebaseVisionText -> processTxt(firebaseVisionText) }.addOnFailureListener { }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap
            if (imageView != null) {
                imageView.setImageBitmap(imageBitmap)
            }
        }
    }
    private fun processTxt(firebaseVisionText: FirebaseVisionText?) {
        val blocks = firebaseVisionText!!.getBlocks()
        if (blocks.size == 0) {
            Toast.makeText(this@MainActivity, "No Text :(", Toast.LENGTH_LONG).show()
            return
        }
        for (block in firebaseVisionText.getBlocks()) {
            val txt = block.getText()
            if (txtView != null) {
                txtView.setTextSize(24F)
                txtView.setText(txt)
            }


        }
    }

    val REQUEST_IMAGE_CAPTURE = 1
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
}
