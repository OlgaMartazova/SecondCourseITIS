package com.itis.secondcourseitis.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.secondcourseitis.databinding.ItemTrackBinding
import com.itis.secondcourseitis.model.Track

class TrackHolder (
    private val binding: ItemTrackBinding,
    private val action: (Int)-> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    private var track: Track? = null

    init{
        itemView.setOnClickListener{
            track?.id?.also(action)
        }
    }


    fun bind(item: Track) {
        track = item

        with(binding) {
            tvTitle.text = item.title
            tvAuthor.text = item.author
            ivCover.setImageResource(item.cover)
        }
    }


    companion object {
        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = TrackHolder(
            ItemTrackBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            action
        )
    }
}
