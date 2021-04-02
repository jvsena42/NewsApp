package com.bulletapps.newsapp.data.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .centerCrop()
        .into(this)
    }

fun View.viewGone(){
    this.visibility = View.GONE
}

fun View.viewVisible(){
    this.visibility = View.VISIBLE
}

