package vn.teko.todo.services

import org.springframework.stereotype.Service
import vn.teko.todo.repositories.NoteRepository
import vn.teko.todo.repositories.toNote
import vn.teko.todo.repositories.toNoteModel
import java.util.*

@Service
class NoteServiceImpl(
    private val noteRepository: NoteRepository,
) : NoteService {

    override fun getNotes(): List<Note> {
        val notes = noteRepository.findAll().map { it.toNote() };
        return notes
    }
}
