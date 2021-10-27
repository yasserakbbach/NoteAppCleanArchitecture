package com.yasserakbbach.noteappcleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yasserakbbach.core.data.Note
import com.yasserakbbach.noteappcleanarchitecture.R
import com.yasserakbbach.noteappcleanarchitecture.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class ListNotesAdapter(val actions: ListAction) : ListAdapter<Note, ListNotesAdapter.NoteViewHolder>(NoteComparator()) {

    inner class NoteViewHolder(private val itemNoteBinding: ItemNoteBinding) : RecyclerView.ViewHolder(itemNoteBinding.root) {

        fun bind(note: Note) {

            itemNoteBinding.apply {
                title.text = note.title
                content.text = note.content
                val lastUpdate = itemNoteBinding.root.context.getString(
                        R.string.last_update,
                        SimpleDateFormat("MMM dd, HH:mm:ss", Locale.ENGLISH).format(Date(note.updateTime))
                    )
                date.text = lastUpdate
                root.setOnClickListener {
                    actions.onClick(note.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        getItem(position)?.let {
            holder.bind(it)
        }
    }

    internal class NoteComparator : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Note, newItem: Note) =
            oldItem.id == newItem.id
    }
}