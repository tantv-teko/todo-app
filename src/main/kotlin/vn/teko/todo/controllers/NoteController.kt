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
    fun getAllnotes(): ResponseEntity<List<NoteDto>> {
        val listNote = notesevice.getNotes()
        val  listNoteDto = listNote.map { Note -> Note.toNoteDto() }
        return ResponseEntity.ok(listNoteDto)
    }

    @GetMapping("/{id}")
    fun findNoteById(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(notesevice.findNoteById(id).toNoteDto())
    }

    @PostMapping
    fun addNotes(
        @RequestBody noteDto: NoteDto,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(notesevice.addNote(noteDto.toNote()).toNoteDto())
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: Long,
        @RequestBody noteDto: NoteDto,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(notesevice.updateNote(id, noteDto.toNote()).toNoteDto())
    }

    @DeleteMapping("/{id}")
    fun  deleteNote(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(notesevice.deleteNote(id).toNoteDto())
    }

}