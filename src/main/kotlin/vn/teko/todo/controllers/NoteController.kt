package vn.teko.todo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import vn.teko.todo.services.NoteService

@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val noteSevice: NoteService,
) {

    @GetMapping
    fun getAllnotes(): ResponseEntity<List<NoteDto>> {
        val noteDtos = noteSevice.getNotes().map { Note -> Note.toNoteDto() }
        return ResponseEntity.ok(noteDtos)
    }
}