package vn.teko.todo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import vn.teko.todo.resquest.AddNoteRequest
import vn.teko.todo.resquest.UpdateNoteRequest
import vn.teko.todo.resquest.toNote
import vn.teko.todo.services.NoteService
import javax.validation.Valid

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

    @PostMapping
    fun addNote(
        @Valid @RequestBody addNoteRequest: AddNoteRequest,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.addNote(addNoteRequest.toNote()).toNoteDto())
    }

    @GetMapping("/{id}")
    fun getNote(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.getNote(id).toNoteDto())
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: Long,
        @Valid @RequestBody updateNoteRequest: UpdateNoteRequest,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.updateNote(id, updateNoteRequest.toNote()).toNoteDto())
    }

    @DeleteMapping("/{id}")
    fun  deleteNote(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.deleteNote(id).toNoteDto())
    }

}