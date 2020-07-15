package com.batcuevasoft.flickrinterestingday.ui.extensions

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setupNoLimitsActionBar(isNoLimitsEnabled: Boolean, toolbar: Toolbar?) {
    if (!isNoLimitsEnabled) {
        toolbar?.setMarginTop(0)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    } else {
        toolbar?.setMarginTop(24.px())
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }
}