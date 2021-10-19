package vn.teko.todo.controllers

data class NoteDto(
    var noteId: Long,
    val title: String?,
    val content: String?
)