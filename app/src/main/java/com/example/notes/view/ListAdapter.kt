package com.example.notes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.data.Note

class ListAdapter : RecyclerView.Adapter<ListAdapter.MainViewHolder>() {

    private var notesList : MutableList<Note> = mutableListOf()

    inner class MainViewHolder(itemView : View) :
        RecyclerView.ViewHolder(itemView) {

        private var name = itemView.findViewById<TextView>(R.id.card_name)

        private var date = itemView.findViewById<TextView>(R.id.card_date)

        fun bind(note : Note){

            name.text = note.name

            date.text = note.date.toString()

        }
    }

    fun setData(notes : MutableList<Note>){
        notesList = notes
        notifyDataSetChanged()
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