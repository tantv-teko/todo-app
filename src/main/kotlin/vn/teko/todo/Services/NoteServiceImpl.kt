package vn.teko.todo.services

import org.springframework.stereotype.Service
import vn.teko.todo.controllers.NoteDto
import vn.teko.todo.repositories.NoteModel
import vn.teko.todo.repositories.NoteRepository
import vn.teko.todo.repositories.toNote
import vn.teko.todo.repositories.toNoteModel

@Service
class NoteServiceImpl(
    private val noteRepository: NoteRepository,
) : NoteService {

    override fun getNotes(): List<Note> {
        val notesModelList = noteRepository.findAll();
        var notesList = mutableListOf<Note>()
        notesModelList.forEach() {
            notesList.add(it.toNote())
        }
        return notesList
    }

    override fun addNote(note: Note): Note {
        return noteRepository.save(note.toNoteModel()).toNote()
    }

}
