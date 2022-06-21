package com.example.a160419132_perpusubaya.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import com.example.a160419132_perpusubaya.model.PerpusDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadImage(url:String?, progbar:ProgressBar){
    Picasso.get().load(url).resize(300, 400).centerCrop().into(this,
        object : Callback {
            override fun onSuccess() {
                progbar.visibility= View.GONE
            }

            override fun onError(e: Exception?) {

            }
        }
    )

}

val DB_NAME = "Perpusdb"
fun buildDb(context: Context): PerpusDatabase {
    val db = Room.databaseBuilder(context, PerpusDatabase::class.java, DB_NAME)
        .build()
    return db
}

@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoUrl(v:ImageView,url:String?,pb:ProgressBar){
    v.loadImage(url,pb)
}