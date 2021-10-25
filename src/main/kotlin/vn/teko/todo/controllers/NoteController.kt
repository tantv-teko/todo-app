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
            listNoteDto.add(it.toNoteDto())
        }
        return ResponseEntity.ok(listNoteDto)
    }

    @PostMapping
    fun addNotes(
        @RequestBody noteDto: NoteDto,
    ): ResponseEntity<NoteDto> {
        println("Post Note")
        return ResponseEntity.ok(notesevice.addNote(noteDto.toNote()).toNoteDto())
    }

    @GetMapping("/{id}")
    fun findNoteById(
        @PathVariable id: Long,
    ): ResponseEntity<NoteDto> {
        return ResponseEntity.ok(notesevice.findNoteById(id).toNoteDto())
    }

    @PutMapping
    fun updateNote(
        @PathVariable id: Long,
        noteDto: NoteDto,
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


























