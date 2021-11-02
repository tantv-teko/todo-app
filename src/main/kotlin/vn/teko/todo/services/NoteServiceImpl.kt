package vn.teko.todo.services

import org.springframework.stereotype.Service
import vn.teko.todo.exception.NotFoundException
import vn.teko.todo.repositories.*
import java.time.LocalDateTime

@Service
class NoteServiceImpl(
    private val noteRepository: NoteRepository,
    private val noteColorRepository: NoteColorRepository,
) : NoteService {

    override fun getNotes(): List<Note> {
        val notes = noteRepository.findAll().map { it.toNote(noteRepository.getnotecolor(it.id)) }
        return notes
    }

    override fun getNote(id: Long): Note {
        val optionalNoteModel = noteRepository.findById(id).orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        return optionalNoteModel.toNote(noteRepository.getnotecolor(optionalNoteModel.id))
    }

    override fun createNote(note: Note): Note {
        val note = noteRepository.save(note.toNoteModel()).toNote(note.colorId)
        noteColorRepository.save(NoteColorModel(
            noteId = note.id,
            colorId = note.colorId
        ))
        return note
    }

    override fun updateNote(id: Long, newNote: Note): Note {
        val optionalNoteModel = noteRepository.findById(id).orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        val note = optionalNoteModel.toNote(noteRepository.getnotecolor(optionalNoteModel.id))
        noteColorRepository.deleteByNoteId(note.id)
        noteColorRepository.save(NoteColorModel(
            noteId = note.id,
            colorId = note.colorId
        ))
        note.apply {
            title = newNote.title
            content = newNote.content
            colorId = newNote.colorId
            editedAt = LocalDateTime.now()
        }
        return noteRepository.save(note.toNoteModel()).toNote(note.colorId)
    }

    override fun deleteNote(id: Long): Note {
        val optionalNoteModel = noteRepository.findById(id).orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        val note  = optionalNoteModel.toNote(noteRepository.getnotecolor(optionalNoteModel.id))
        noteRepository.deleteById(id)
        return note
    }
}
