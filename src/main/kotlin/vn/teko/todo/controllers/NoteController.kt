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
        val noteDtos = noteSevice.getNotes().map { it.toNoteDto() }
        return ResponseEntity.ok(noteDtos)
    }

    @GetMapping("/{id}")
    fun findNoteById(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.findNoteById(id).toNoteDto())
    }

    @PostMapping
    fun addNotes(
        @RequestBody noteDto: NoteDto,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.addNote(noteDto.toNote()).toNoteDto())
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: Long,
        @RequestBody noteDto: NoteDto,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.updateNote(id, noteDto.toNote()).toNoteDto())
    }

    @DeleteMapping("/{id}")
    fun  deleteNote(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.deleteNote(id).toNoteDto())
    }

}