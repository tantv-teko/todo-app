package vn.teko.todo.services

import java.util.Date

data class Note(
    val noteId: Long,
    var title: String? = "",
    var content: String? = "",
    val createAt: Date?,
    var editedAt: Date?,
)
