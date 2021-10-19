package vn.teko.todo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import vn.teko.todo.Services.NoteService

@RestController
class NoteController(
    @Autowired
    private val notesevice: NoteService) {

    @GetMapping("GET/api/notes")
    fun getAllnotes() = notesevice.getAllnotes()
    
    @PostMapping("POST/api/notes")
    fun addNote(@RequestParam title: String?, @RequestParam content: String?) = notesevice.addNote(title, content)
}