package vn.teko.todo.Services

import vn.teko.todo.controllers.NoteDto

interface NoteService {
    fun getAllnotes() : MutableList<NoteDto>;
    fun addNote(title: String?, content: String?);
}