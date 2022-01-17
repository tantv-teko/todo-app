package vn.teko.todo.services

interface NoteService {
    suspend fun getNotes() : List<Note>
    suspend fun getNote(id: Long) : Note
    suspend fun createNote(note: Note): Note
    suspend fun updateNote(id: Long, newNote: Note) : Note
    suspend fun deleteNote(id: Long) : Note
}
