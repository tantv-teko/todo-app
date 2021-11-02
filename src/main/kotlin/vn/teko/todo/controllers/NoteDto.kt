package vn.teko.todo.controllers

import vn.teko.todo.services.Color
import vn.teko.todo.services.Note
import java.time.LocalDateTime

data class NoteDto(
    val id: Long,
    var title: String,
    var content: String,
    val color: Color,
    val createAt: LocalDateTime = LocalDateTime.now(),
    var editedAt: LocalDateTime = LocalDateTime.now(),
)

fun Note.toNoteDto(color: Color) = NoteDto(
    id = this.id,
    title = this.title,
    content = this.content,
    color = color,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

