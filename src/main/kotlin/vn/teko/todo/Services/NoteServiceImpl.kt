package vn.teko.todo.Services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import vn.teko.todo.controllers.NoteDto
import vn.teko.todo.repositories.NoteModel
import vn.teko.todo.repositories.NoteRepository

@Service
class NoteServiceImpl(
    @Autowired private val noterepository: NoteRepository,
    @Autowired private val noteModel: NoteModel) : NoteService{

    override fun getAllnotes(): MutableList<NoteDto> {
        val notesList = noterepository.findAll();
        var notesDtoList : MutableList<NoteDto> = mutableListOf()
        notesList.forEach() {
            notesDtoList.add(noteModel.mapToDto(it))
        }
        return notesDtoList
    }
    override fun addNote(title: String?, content: String?) {
        val note = Note(title, content)
        noterepository.save(note)
    }

}