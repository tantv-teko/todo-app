package vn.teko.todo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vn.teko.todo.exception.NotFoundException
import vn.teko.todo.repositories.*
import java.time.LocalDateTime

@Service
@Transactional
class NoteServiceImpl(
    private val noteRepository: NoteRepository,
    private val noteColorRepository: NoteColorRepository,
    private val colorRepository: ColorRepository,
    private val noteLabelRepository: NoteLabelRepository,
    private val labelRepository: LabelRepository,
) : NoteService {

    override fun getNotes(): List<Note> {
        val noteModels = noteRepository.findAll()
        val notes = noteRepository.findAll().map { it.toNote(colorRepository.findByNoteId(it.id).toColor()) }
        notes.map {
            it.labels = labelRepository.findByNoteId(it.id).map { it.toLabel() }
        }
        return notes
    }

    override fun getNote(id: Long): Note {
        val noteModel = noteRepository.findById(id)
                .orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        val note = noteModel.toNote(colorRepository.findByNoteId(noteModel.id).toColor())
        note.apply {
            labels = labelRepository.findByNoteId(note.id).map { it.toLabel() }
        }
        return note
    }

    override fun createNote(addNote: Note): Note {
        if (addNote.color.id == 0L) {
            addNote.color = colorRepository.findByDefault().toColor()
        }
        else {
            addNote.color = colorRepository.findById(addNote.color.id)
                .orElseThrow { NotFoundException(message = "not found colorid = ${addNote.color.id} ") }
                .toColor()
        }
        addNote.labels.forEach {
            labelRepository.findById(it.id)
                .orElseThrow { NotFoundException(message = "not found labelid = ${it.id} ") }
                .toLabel()
        }
        val note = noteRepository.save(addNote.toNoteModel()).toNote(addNote.color)

        noteColorRepository.save(NoteColorModel(
                noteId = note.id,
                colorId = note.color.id
        ))
        note.apply {
            color = colorRepository.findByNoteId(note.id).toColor()
            labels = addNote.labels
        }

        note.labels.forEach {
            noteLabelRepository.save(NoteLabelModel(
                noteId = note.id,
                labelId = it.id,
            ))
        }
        note.apply {
            labels = labelRepository.findByNoteId(note.id).map { it.toLabel() }
        }

        return note
    }


    override fun updateNote(id: Long, newNote: Note): Note {
        var note = this.getNote(id)
        note.apply {
            this.title = newNote.title
            this.content = newNote.content
            this.color = colorRepository.findById(newNote.color.id)
                    .orElseThrow { NotFoundException(message = "not found colorid = $id ") }
                    .toColor()
            this.labels = newNote.labels
            this.editedAt = LocalDateTime.now()
        }
        noteColorRepository.deleteByNoteId(note.id)
        noteColorRepository.save(NoteColorModel(
            noteId = note.id,
            colorId = note.color.id
        ))

        noteRepository.save(note.toNoteModel())
        noteLabelRepository.deleteNoteLabelModelByNoteId(note.id)
        note.labels.forEach {
            noteLabelRepository.save(NoteLabelModel(
                noteId = note.id,
                labelId = it.id,
            ))
        }
        note.apply {
            labels = labelRepository.findByNoteId(note.id).map { it.toLabel() }
        }

        return note
    }

    override fun deleteNote(id: Long): Note {
        val noteModel = noteRepository.findById(id)
                .orElseThrow { NotFoundException(message = "not found noteId = $id ") }
        var note = this.getNote(id)
        noteRepository.deleteById(id)
        return note
    }

}
