package com.example.notes.data

import android.R.attr

object RepositoryImpl : IRepository {

    var notes : MutableList<Note> = mutableListOf(
        Note(1,"test", "test")
    )

    override fun addNote(name : String, description : String){
        val newId = notes.size + 1

        val note = Note(newId, name, description)
        notes.add(0, note)
    }

    override fun deleteNote(note : Note){
        notes.remove(note)
    }

    override fun getList() : MutableList<Note>{
        return notes
    }

    override fun updateNote(note: Note, name: String, description: String) : Note{

        for (i in 0 until notes.size) {

            val item: Note = notes.get(i)

            if (item.id.equals(note.id)) {

                var nameToSet: String = item.name

                var descriptionToSet: String = item.description

                if (name != null) {
                    nameToSet = name
                }

                if (attr.description != null) {
                    descriptionToSet = description
                }

                val newNote = Note(item.id, nameToSet, descriptionToSet)
                notes.removeAt(i)
                notes.add(i, newNote)

                return newNote
            }
        }
        return note
    }
}