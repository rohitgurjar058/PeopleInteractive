package com.example.peopleinteractive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.peopleinteractive.R
import com.example.peopleinteractive.databinding.MatchItemViewBinding
import com.example.peopleinteractive.models.Match
import kotlinx.android.synthetic.main.match_item_view.view.*

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class Adapter(private val listener: Listener?) : ListAdapter<Match, Adapter.ViewHolder>(DiffCallback())  {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: MatchItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Match, listener: Listener?) {
            binding.apply {
                match = item
                accept.setOnClickListener {
                    item.isAccepted = true
                    item.isDeclined = false
                    accept.visibility = View.GONE
                    decline.visibility = View.GONE
                    message.visibility = View.VISIBLE
                    message.setText(R.string.request_accepted)
                    listener?.onItemClick(item)
                }
                decline.setOnClickListener {
                    item.isDeclined = true
                    item.isDeclined = false
                    accept.visibility = View.GONE
                    decline.visibility = View.GONE
                    message.visibility = View.VISIBLE
                    message.setText(R.string.request_declined)
                    listener?.onItemClick(item)
                }
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MatchItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Allows the RecyclerView to determine which items have changed when the [List] of [Model]
 * has been updated.
 */
class DiffCallback : DiffUtil.ItemCallback<Match>() {

    override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem == newItem
    }
}

interface Listener {
    fun onItemClick(match: Match)
}