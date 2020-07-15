package com.batcuevasoft.flickrinterestingday.ui.main.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batcuevasoft.flickrinterestingday.R
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.batcuevasoft.flickrinterestingday.ui.extensions.load
import com.batcuevasoft.flickrinterestingday.ui.extensions.visible
import com.batcuevasoft.flickrinterestingday.ui.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_picture.view.*


class FlickrPictureAdapter : BaseAdapter<FlickrPicture, FlickrPictureAdapter.FlickrPictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPictureViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false).run {
            return FlickrPictureViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FlickrPictureViewHolder, position: Int) {
        holder.bind(elementList[position])
    }

    inner class FlickrPictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(flickrPicture: FlickrPicture) {
            if(flickrPicture.isPublic) {
                itemView.tvIsPublic.visible()
            }
            itemView.pictureName.text = flickrPicture.name
            itemView.picImage.load(flickrPicture.pictureUrl)
        }
    }
}