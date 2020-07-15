package com.batcuevasoft.flickrinterestingday.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batcuevasoft.flickrinterestingday.R
import kotlinx.android.synthetic.main.load_state_item.view.*

class FlickrLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FlickrLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) =
        LayoutInflater.from(parent.context).inflate(R.layout.load_state_item, parent, false).run {
            LoadStateViewHolder(this, retry)
        }

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

    inner class LoadStateViewHolder(
        itemView: View,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.retry_button.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                itemView.error_msg.text = loadState.error.localizedMessage
            }

            itemView.progress_bar.isVisible = loadState is LoadState.Loading
            itemView.retry_button.isVisible = loadState is LoadState.Error
            itemView.error_msg.isVisible = loadState is LoadState.Error
        }
    }
}