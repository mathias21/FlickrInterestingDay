package com.batcuevasoft.flickrinterestingday.ui.picturedetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.batcuevasoft.flickrinterestingday.R
import com.batcuevasoft.flickrinterestingday.ui.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.flickr_picture_detail_fragment.view.*


class FlickrPictureDetailFragment
    : BaseFragment<FlickrPictureDetailViewModel>(R.layout.flickr_picture_detail_fragment, FlickrPictureDetailViewModel::class) {

    private val args: FlickrPictureDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()

        with(args.picture) {
            ViewCompat.setTransitionName(view.pictureName, this.name)
            ViewCompat.setTransitionName(view.imageView, this.pictureUrl)

            startEnterTransitionAfterLoadingImage(pictureUrl, view.imageView)

            view.pictureName.text = name

            (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.pic_detail)
        }

    }

    override fun attachObservers() {

    }

    private fun startEnterTransitionAfterLoadingImage(
        imageAddress: String,
        imageView: ImageView
    ) {
        Glide.with(this)
            .load(imageAddress)
            .centerCrop()
            .dontAnimate() // 1
            .listener(object : RequestListener<Drawable> { // 2
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: com.bumptech.glide.request.target.Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(imageView)
    }

}