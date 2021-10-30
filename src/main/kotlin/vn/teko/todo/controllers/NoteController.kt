package vn.teko.todo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import vn.teko.todo.resquest.AddNoteRequest
import vn.teko.todo.resquest.UpdateNoteRequest
import vn.teko.todo.services.ColorService
import vn.teko.todo.services.Note
import vn.teko.todo.services.NoteService
import javax.validation.Valid

@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val noteSevice: NoteService,
    val colorService: ColorService,
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
    fun createNote(
        @Valid @RequestBody request: AddNoteRequest,
    ): ResponseEntity<NoteDto> {
        println("add note")
        val note = Note(
            id = request.id,
            title = request.title,
            content = request.content,
            color = colorService.getColor(request.colorId),
            createAt = request.createAt,
            editedAt = request.editedAt,
        )
        return ResponseEntity.ok(noteSevice.createNote(note).toNoteDto())
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateNoteRequest,
    ): ResponseEntity<NoteDto> {
        print("update note")
        val note = Note(
            id = request.id,
            title = request.title,
            content = request.content,
            color = colorService.getColor(request.colorId),
            createAt = request.createAt,
            editedAt = request.editedAt,
        )
        return ResponseEntity.ok(noteSevice.updateNote(id, note).toNoteDto())
    }

    @DeleteMapping("/{id}")
    fun  deleteNote(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(noteSevice.deleteNote(id).toNoteDto())
    }

}
