package vn.teko.todo.services

import java.time.LocalDateTime

data class Note(
    val id: Long,
    var title: String? = "",
    var content: String? = "",
    val createAt: LocalDateTime,
    var editedAt: LocalDateTime,
)
