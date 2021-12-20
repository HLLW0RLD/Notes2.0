package com.example.notes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.data.Note

class ListAdapter(var fragment : Fragment): RecyclerView.Adapter<ListAdapter.MainViewHolder>() {

    private var notesList: MutableList<Note> = mutableListOf()

    private var clickedNote: ListFragment.onNoteClick? = null

    private var longClickedNote: ListFragment.onNoteLongClick? = null

    fun setNoteClickListener(click: ListFragment.onNoteClick) {
        this.clickedNote = click
    }

    fun setNoteLongClickListener(longClick: ListFragment.onNoteLongClick) {
        this.longClickedNote = longClick
    }

    inner class MainViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

            private var name = itemView.findViewById<TextView>(R.id.card_name)

            private var date = itemView.findViewById<TextView>(R.id.card_date)

            fun bind(note: Note) {

                fragment.registerForContextMenu(itemView)

                name.text = note.name

                itemView.setOnClickListener { clickedNote?.onNoteClick(note) }

                itemView.setOnLongClickListener {
                    longClickedNote?.onNoteLongClick(note, adapterPosition)
                    itemView.showContextMenu()
                }

            }
    }



    fun setData(notes : MutableList<Note>){
        notesList = notes
        notifyDataSetChanged()
    }

    fun add(addedNote : Note){
        notesList.add(0, addedNote)
    }

    fun remove(removedNote : Note){
        notesList.remove(removedNote)
    }

    fun update(note: Note) {
        for (i in 0 until notesList.size) {
            val item: Note = notesList.get(i)
            if (item.id.equals(note.id)) {
                notesList.removeAt(i)
                notesList.add(i, note)
                return
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}