package vn.teko.todo.services

interface NoteService {
    fun getNotes() : List<Note>
    fun addNote(note: Note): Note
    fun getNote(id: Long) : Note
    fun updateNote(id: Long, newNote: Note) : Note
    fun deleteNote(id: Long) : Note
}
