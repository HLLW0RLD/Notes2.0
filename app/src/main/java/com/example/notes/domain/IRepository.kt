package com.example.notes.domain

import com.example.notes.data.Note

interface IRepository {

    fun addNote(name : String, description : String, date : Int)

    fun deleteNote(note: Note)

    fun getList() : List<Note>

}