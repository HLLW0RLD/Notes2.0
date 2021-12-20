package com.example.notes.data

import com.example.notes.data.Note
import java.util.*

interface IRepository {

    fun addNote(name : String, description : String)

    fun deleteNote(note: Note)

    fun getList() : List<Note>

    fun updateNote(note : Note, name : String, description : String) : Note

}