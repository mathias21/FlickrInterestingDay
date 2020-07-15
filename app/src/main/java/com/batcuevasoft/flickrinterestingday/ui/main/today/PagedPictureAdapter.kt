package com.batcuevasoft.flickrinterestingday.ui.main.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batcuevasoft.flickrinterestingday.R
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.batcuevasoft.flickrinterestingday.ui.extensions.gone
import com.batcuevasoft.flickrinterestingday.ui.extensions.load
import com.batcuevasoft.flickrinterestingday.ui.extensions.visible
import kotlinx.android.synthetic.main.flickr_picture_detail_fragment.view.*
import kotlinx.android.synthetic.main.item_picture.view.*
import kotlinx.android.synthetic.main.item_picture.view.pictureName

class PagedPictureAdapter : PagingDataAdapter<FlickrPicture, PagedPictureAdapter.FlickrPictureViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FlickrPicture>() {
            override fun areItemsTheSame(
                oldPicture: FlickrPicture,
                newPicture: FlickrPicture
            ) = oldPicture.id == newPicture.id

            override fun areContentsTheSame(
                oldPicture: FlickrPicture,
                newPicture: FlickrPicture
            ) = oldPicture == newPicture
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPictureViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false).run {
            return FlickrPictureViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FlickrPictureViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        } ?: holder.clear()
    }

    inner class FlickrPictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    val action = TodayFragmentDirections.actionTodayFragmentToPictureDetail(it)
                    val extras = FragmentNavigatorExtras(
                        itemView.pictureName to it.name,
                        itemView.picImage to it.pictureUrl)
                    itemView.findNavController().navigate(action, extras)
                }
            }
        }

        fun bind(flickrPicture: FlickrPicture) {
            if (flickrPicture.isPublic) {
                itemView.tvIsPublic.visible()
            }
            itemView.pictureName.run {
                text = flickrPicture.name
                ViewCompat.setTransitionName(this, flickrPicture.name)
            }
            itemView.picImage.run {
                load(flickrPicture.pictureUrl)
                ViewCompat.setTransitionName(this, flickrPicture.pictureUrl)
            }
        }
        fun clear() {
            itemView.tvIsPublic.gone()
            itemView.pictureName.text = ""
            itemView.picImage.setImageResource(0)
        }

    }
}