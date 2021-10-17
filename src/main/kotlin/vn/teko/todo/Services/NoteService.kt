package vn.teko.todo.Services

interface NoteService {
    fun getAllnotes() : MutableIterable<Note>
}