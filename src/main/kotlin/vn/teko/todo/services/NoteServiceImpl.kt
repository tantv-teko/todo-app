package vn.teko.todo.services

import org.springframework.stereotype.Service
import vn.teko.todo.repositories.NoteRepository
import vn.teko.todo.repositories.toNote
import vn.teko.todo.repositories.toNoteModel
import java.time.LocalDateTime

@Service
class NoteServiceImpl(
    private val noteRepository: NoteRepository,
) : NoteService {

    override fun getNotes(): List<Note> {
        val notes = noteRepository.findAll().map { it.toNote() }
        return notes
    }

    override fun addNote(note: Note): Note {
        return noteRepository.save(note.toNoteModel()).toNote()
    }

    override fun findNoteById(id: Long): Note {
        return  noteRepository.findNoteModelById(id).toNote()
    }

    override fun updateNote(id: Long, newNote: Note): Note {
        var note = noteRepository.findNoteModelById(id).toNote()
        note.title = newNote.title
        note.content = newNote.content
        note.editedAt = LocalDateTime.now()
        return noteRepository.save(note.toNoteModel()).toNote()
    }

    override fun deleteNote(id: Long): Note {
        var note = noteRepository.findNoteModelById(id).toNote()
        noteRepository.deleteById(id)
        return note
    }
}
