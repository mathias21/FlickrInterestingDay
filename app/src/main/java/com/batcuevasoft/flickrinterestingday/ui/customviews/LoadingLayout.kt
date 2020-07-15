package com.batcuevasoft.flickrinterestingday.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.batcuevasoft.flickrinterestingday.R
import kotlinx.android.synthetic.main.loading_layout.view.*

class LoadingLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        addView(View.inflate(context, R.layout.loading_layout, null))
    }

    override fun setVisibility(visibility: Int) {
        if (visibility == View.VISIBLE) {
            loadingAnimation?.playAnimation()
        } else {
            loadingAnimation?.cancelAnimation()
        }
        super.setVisibility(visibility)
    }
}