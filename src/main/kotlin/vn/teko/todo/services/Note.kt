package vn.teko.todo.services

import java.time.LocalDateTime

data class Note(
    val id: Long,
    var title: String,
    var content: String,
    var colorId: Long,
    val createAt: LocalDateTime,
    var editedAt: LocalDateTime,
)
