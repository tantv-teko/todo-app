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
        println("getAllNotes");
        val listNote = notesevice.getNotes()
        var listNoteDto = mutableListOf<NoteDto>()
        listNote.forEach() {
            listNoteDto.add(it.toDto())
        }
        return ResponseEntity.ok(listNoteDto)
    }


    @PostMapping
    fun addNotes(
        @RequestBody noteDto: NoteDto,
    ): ResponseEntity<NoteDto> {
        println("Post Note")
        return ResponseEntity.ok(notesevice.addNote(noteDto.toNote()).toDto())
    }


}


























