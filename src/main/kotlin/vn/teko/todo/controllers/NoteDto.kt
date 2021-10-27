package vn.teko.todo.controllers

import vn.teko.todo.services.Note
import java.time.LocalDateTime

data class NoteDto(
    val id: Long,
    var title: String? = "",
    var content: String? = "",
    val createAt: LocalDateTime = LocalDateTime.now(),
    var editedAt: LocalDateTime = LocalDateTime.now(),
)

fun Note.toNoteDto() = NoteDto(
    id = this.id,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

fun NoteDto.toNote() = Note(
    id = this.id,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)