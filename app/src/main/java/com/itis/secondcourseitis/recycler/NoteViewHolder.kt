package com.itis.secondcourseitis.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.secondcourseitis.ExtConstants.DATE
import com.itis.secondcourseitis.ExtConstants.DESC
import com.itis.secondcourseitis.ExtConstants.TITLE
import com.itis.secondcourseitis.databinding.ItemNoteBinding
import com.itis.secondcourseitis.model.Note
import com.itis.secondcourseitis.recycler.DateConverter.convertDate

class NoteViewHolder(
    private val binding: ItemNoteBinding,
    private val delete: (Int) -> Unit,
    private val clickOnItem: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var note: Note? = null

    init {
        itemView.setOnClickListener {
            note?.id?.also(clickOnItem)
        }
    }

    fun bind(item: Note) {
        note = item

        with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.desc
            tvDate.text = convertDate(item.date)

            btnDelete.setOnClickListener {
                note?.id?.also(delete)
            }
        }
    }


    fun updateFields(bundle: Bundle) {
        bundle.run {
            getString(TITLE)?.also {
                updateTitle(it)
            }
            getString(DESC)?.also {
                updateDesc(it)
            }
            getString(DATE)?.also {
                updateDate(it)
            }
        }
    }

    private fun updateTitle(title: String) {
        binding.tvTitle.text = title
    }

    private fun updateDesc(desc: String) {
        binding.tvDesc.text = desc
    }

    private fun updateDate(date: String) {
        binding.tvDate.text = date
    }

    companion object {
        fun create(
            parent: ViewGroup,
            delete: (Int) -> Unit,
            clickOnItem: (Int) -> Unit
        ) = NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            delete,
            clickOnItem
        )
    }
}
