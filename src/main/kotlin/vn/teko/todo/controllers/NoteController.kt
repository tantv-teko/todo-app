package vn.teko.todo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import vn.teko.todo.services.NoteService

@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val notesevice: NoteService,
) {

    @GetMapping
    fun getAllnotes() : ResponseEntity<List<NoteDto>> {
        val listNote = notesevice.getNotes()
        var listNoteDto = mutableListOf<NoteDto>()
        listNote.forEach() {
            listNoteDto.add(it.toNoteDto())
        }
        return ResponseEntity.ok(listNoteDto)
    }

}


























