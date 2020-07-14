package com.batcuevasoft.flickrinterestingday.ui.extensions

import android.webkit.URLUtil
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.load(url: String) {
    if (URLUtil.isValidUrl(url)) {
        Glide.with(this.context).load(url).into(this)
    }
}