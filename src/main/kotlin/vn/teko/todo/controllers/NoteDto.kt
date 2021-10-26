package vn.teko.todo.controllers

import com.fasterxml.jackson.annotation.JsonFormat
import vn.teko.todo.services.Note
import java.util.Date

data class NoteDto(
    val id: Long,
    var title: String? = "",
    var content: String? = "",
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    val createAt: Date?,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    var editedAt: Date?,
)

fun Note.toNoteDto() = NoteDto(
    id = this.noteId,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

fun NoteDto.toNote() = Note(
    noteId = this.id,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)