package com.batcuevasoft.flickrinterestingday.ui.extensions

import android.content.res.Resources

fun Int.dp() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.px() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.dp() = (this / Resources.getSystem().displayMetrics.density)

fun Float.px() = (this * Resources.getSystem().displayMetrics.density)