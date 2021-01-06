package com.example.peopleinteractive.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.peopleinteractive.R
import com.example.peopleinteractive.adapter.Adapter
import com.example.peopleinteractive.models.Match

/**
 * When there is no data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Match>?) {
    if (recyclerView.adapter is Adapter) {
        val adapter = recyclerView.adapter as Adapter
        adapter.submitList(data)
    }
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .centerCrop()
                .apply(RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.avtar_image)
                    .error(R.drawable.avtar_image))
                .into(imgView)
    }
}

/**
 * This binding adapter displays the [ApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

/**
 * Message Visibility
 */
@BindingAdapter("textVisibility")
fun bindVisibility(view: TextView, match: Match) {
    if(match.isAccepted || match.isDeclined) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("btnVisibility")
fun bindBtnVisibility(view: Button, match: Match) {
    if (match.isAccepted || match.isDeclined) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

/**
 *  Set Accept/Decline Message
 */
@BindingAdapter("setText")
fun bindTextMessage(view: TextView, match: Match) {
    if (match.isAccepted)
        view.text = view.context.getString(R.string.request_accepted)
    else if(match.isDeclined)
        view.text = view.context.getString(R.string.request_declined)
}
