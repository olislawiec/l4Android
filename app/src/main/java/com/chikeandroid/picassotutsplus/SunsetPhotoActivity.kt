package com.chikeandroid.picassotutsplus

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso


class SunsetPhotoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SUNSET_PHOTO = "SunsetPhotoActivity.EXTRA_SUNSET_PHOTO"
    }

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var sunsetPhoto: SunsetPhoto
    private lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sunset_photo)

        sunsetPhoto = intent.getParcelableExtra(EXTRA_SUNSET_PHOTO)
        imageView = findViewById(R.id.image)
        textView = findViewById(R.id.text)
        textView.text = sunsetPhoto.opis
        ratingBar = findViewById(R.id.ratingBar)

    }

    override fun onStart() {
        super.onStart()

        Picasso.get()
                .load(sunsetPhoto.url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .priority(Picasso.Priority.HIGH)
                .into(imageView)

        Picasso.get()
                .load(sunsetPhoto.url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .priority(Picasso.Priority.LOW)
                .into(imageView)

        Picasso.get()
                .load(sunsetPhoto.url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .priority(Picasso.Priority.NORMAL)
                .into(imageView)
    }

      override fun onBackPressed() {
        super.onBackPressed()
          var starsNum= ratingBar.numStars
          sunsetPhoto.ocena=starsNum

          val intent = Intent(applicationContext, MainActivity::class.java).apply {
              putExtra("ocena", sunsetPhoto.ocena)
              putExtra("id",sunsetPhoto.id)
          }
          startActivity(intent)
      }

    fun onPalette(palette: Palette?) {
        if (null != palette) {
            val parent = imageView.parent as ViewGroup
            parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY))
        }
    }
}


