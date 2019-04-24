package com.chikeandroid.picassotutsplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageGalleryAdapter: ImageGalleryAdapter
    public lateinit var listaOcen: ArrayList<SunsetPhoto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = GridLayoutManager(this, 2)
        listaOcen= SunsetPhoto.getSunsetPhotos()
        recyclerView = findViewById(R.id.rv_images)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        imageGalleryAdapter = ImageGalleryAdapter(this, listaOcen)
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = imageGalleryAdapter
    }
    override fun onResume(){
        super.onResume()
        val extras= intent.extras
        val rate = extras.getInt("ocena")
        val id = extras.getInt("id")
        for (x in listaOcen){
            if (x.id==id){
                x.ocena=rate
            }
        }
        listaOcen.sortBy { rate }
        imageGalleryAdapter.notifyDataSetChanged()
    }

    private inner class ImageGalleryAdapter(val context: Context, val sunsetPhotos: ArrayList<SunsetPhoto>)
        : RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageGalleryAdapter.MyViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val photoView = inflater.inflate(R.layout.item_image, parent, false)
            return MyViewHolder(photoView)
        }

        override fun onBindViewHolder(holder: ImageGalleryAdapter.MyViewHolder, position: Int) {
            val sunsetPhoto = sunsetPhotos[position]
            val imageView = holder.photoImageView

            Picasso.get()
                    .load(sunsetPhoto.url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .fit()
                    .tag(context)
                    .into(imageView)

        }

        override fun getItemCount(): Int {
            return sunsetPhotos.size
        }


        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var photoImageView: ImageView = itemView.findViewById(R.id.iv_photo)

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(view: View) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val sunsetPhoto = sunsetPhotos[position]
                    val intent = Intent(context, SunsetPhotoActivity::class.java).apply {
                        putExtra(SunsetPhotoActivity.EXTRA_SUNSET_PHOTO, sunsetPhoto)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}
