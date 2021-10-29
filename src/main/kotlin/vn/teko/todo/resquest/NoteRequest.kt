package vn.teko.todo.resquest

import vn.teko.todo.services.Note
import java.time.LocalDateTime

data class NoteRequest(
    val id: Long,
    var title: String? = "",
    var content: String? = "",
    val createAt: LocalDateTime = LocalDateTime.now(),
    var editedAt: LocalDateTime = LocalDateTime.now(),
)

fun NoteRequest.toNote() = Note(
    id = this.id,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)