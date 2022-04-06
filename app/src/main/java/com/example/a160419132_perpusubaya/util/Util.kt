package com.example.a160419132_perpusubaya.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadImage(url:String, progbar:ProgressBar){
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