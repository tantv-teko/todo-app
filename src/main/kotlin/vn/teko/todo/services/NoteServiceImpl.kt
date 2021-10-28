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
        val optionalNoteModel = noteRepository.findById(id)
        optionalNoteModel.orElseThrow { NotFoundException("$id not found") }
        return optionalNoteModel.map { it.toNote() }.get()
    }

    override fun updateNote(id: Long, newNote: Note): Note {
        val optionalNoteModel = noteRepository.findById(id)
        optionalNoteModel.orElseThrow { NotFoundException("$id not found") }
        val note = optionalNoteModel.map { it.toNote() }.get()
        note.title = newNote.title
        note.content = newNote.content
        note.editedAt = LocalDateTime.now()
        return noteRepository.save(note.toNoteModel()).toNote()
    }

    override fun deleteNote(id: Long): Note {
        val optionalNoteModel = noteRepository.findById(id)
        optionalNoteModel.orElseThrow { NotFoundException("$id not found") }
        val note = optionalNoteModel.map { it.toNote() }.get()
        noteRepository.deleteById(id)
        return note
    }
}
