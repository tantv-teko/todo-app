package vn.teko.todo.services

import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
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

    override suspend fun getNotes(): List<Note> {
        val uri = "https://run.mocky.io/v3/90431f7d-485f-4420-b783-f674a4c733c7"
        val restTemplate = RestTemplate()
        val labeldefaul = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            Label::class.java
        )

        val notes = noteRepository.findAll().toList().map { it.toNote(colorRepository.findByNoteId(it.id).toColor()) }
        notes.map {
            it.labels = labelRepository.findByNoteId(it.id).map { it.toLabel() }
            it.labelDefaul = labeldefaul.body!!
        }
        return notes
    }

    override suspend fun getNote(id: Long): Note {
        val noteModel = noteRepository.findById(id) ?: throw NotFoundException(message = "not found noteId = $id ")
        val note = noteModel.toNote(colorRepository.findByNoteId(noteModel.id).toColor())
        note.apply {
            labels = labelRepository.findByNoteId(note.id).map { it.toLabel() }
        }

        val uri = "https://run.mocky.io/v3/90431f7d-485f-4420-b783-f674a4c733c7"
        val restTemplate = RestTemplate()
        val labeldefaul = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            Label::class.java
        )
        note.labelDefaul = labeldefaul.body!!

        return note
    }

    override suspend fun createNote(addNote: Note): Note {
        if (addNote.color.id == 0L) {
            addNote.color = colorRepository.findByDefault().toColor()
        }
        else {
            val colorModel = colorRepository.findById(addNote.color.id) ?: throw NotFoundException(message = "not found colorid = ${addNote.color.id} ")
            addNote.color = colorModel.toColor()
        }
        addNote.labels.forEach {
            val label = labelRepository.findById(it.id) ?: throw NotFoundException(message = "not found labelid = ${it.id} ")
            label.toLabel()
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


    override suspend fun updateNote(id: Long, newNote: Note): Note {
        val note = this.getNote(id)
        val colorModel = colorRepository.findById(newNote.color.id) ?: throw NotFoundException(message = "not found colorid = $id ")
        note.apply {
            this.title = newNote.title
            this.content = newNote.content
            this.color = colorModel.toColor()
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

    override suspend fun deleteNote(id: Long): Note {
        val note = this.getNote(id)
        noteRepository.deleteById(id)
        return note
    }

}
