package com.example.notes.domain

import com.example.notes.data.Note

class RepositoryImpl : IRepository {

    var notes : MutableList<Note> = mutableListOf(
        Note("test", "test", 10),
        Note("test", "test", 10),
        Note("test", "test", 10),
        Note("test", "test", 10),
        Note("test", "test", 10),
        Note("test", "test", 10),
        Note("test", "test", 10),
        Note("test", "test", 10),
    )

    override fun addNote(name : String, description : String, date : Int){
        val note = Note(name, description, date)
        notes.add(note)
    }

    override fun deleteNote(note : Note){
        notes.remove(note)
    }

    override fun getList() : MutableList<Note>{
        return notes
    }
}