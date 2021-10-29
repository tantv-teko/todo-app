package vn.teko.todo.services

import org.springframework.stereotype.Service
import vn.teko.todo.exception.NotFoundException
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

    override fun getNote(id: Long): Note {
        val optionalNoteModel = noteRepository.findById(id).orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        return optionalNoteModel.toNote()
    }

    override fun updateNote(id: Long, newNote: Note): Note {
        val optionalNoteModel = noteRepository.findById(id).orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        val note = optionalNoteModel.toNote()
        note.apply {
            title = newNote.title
            content = newNote.content
            editedAt = LocalDateTime.now()
        }
        return noteRepository.save(note.toNoteModel()).toNote()
    }

    override fun deleteNote(id: Long): Note {
        val optionalNoteModel = noteRepository.findById(id).orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        noteRepository.deleteById(id)
        return optionalNoteModel.toNote()
    }
}
