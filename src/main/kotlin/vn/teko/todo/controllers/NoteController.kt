package vn.teko.todo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import vn.teko.todo.repositories.toNote
import vn.teko.todo.repositories.toNoteModel
import vn.teko.todo.resquest.NoteRequest
import vn.teko.todo.resquest.toNote
import vn.teko.todo.services.NoteService

@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val noteSevice: NoteService,
) {

    @GetMapping
    fun getNotes(): ResponseEntity<List<NoteDto>> {
        val noteDtos = noteSevice.getNotes().map { it.toNoteDto() }
        return ResponseEntity.ok(noteDtos)
    }

    @GetMapping("/{id}")
    fun getNote(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.getNote(id).toNoteDto())
    }

    @PostMapping
    fun addNote(
        @RequestBody noteRequest: NoteRequest,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.addNote(noteRequest.toNote()).toNoteDto())
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: Long,
        @RequestBody noteRequest: NoteRequest,
    ): ResponseEntity<NoteDto> {
        var note = noteSevice.getNote(id)
        note.title = noteRequest.title
        note.content = noteRequest.content
        return ResponseEntity.ok(noteSevice.updateNote(note).toNoteDto())
    }

    @DeleteMapping("/{id}")
    fun  deleteNote(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        val noteDto = noteSevice.getNote(id).toNoteDto()
        noteSevice.deleteNote(id)
        return ResponseEntity.ok(noteDto)
    }

}