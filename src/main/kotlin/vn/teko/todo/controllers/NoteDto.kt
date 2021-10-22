package vn.teko.todo.controllers

import vn.teko.todo.repositories.toNote
import vn.teko.todo.services.Note

data class NoteDto(
    val id: Long,
    var title: String? = "",
    var content: String? ="",
    var colorId: Long,
    val createAt: String?,
    var editedAt: String?,
)

fun Note.toDto() = NoteDto(
    id = this.noteId,
    title = this.title,
    content = this.content,
    colorId = this.colorId,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

fun NoteDto.toNote() = Note(
    noteId = this.id,
    title = this.title,
    content = this.content,
    colorId = this.colorId,
    createAt = this.createAt,
    editedAt = this.editedAt,
)