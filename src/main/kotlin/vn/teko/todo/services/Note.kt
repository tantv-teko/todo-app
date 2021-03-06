package vn.teko.todo.services

import java.time.LocalDateTime

data class Note(
    val id: Long,
    var title: String,
    var content: String,
    var color: Color = Color(
            id = 0,
            name = " ",
            code = " ",
            isDefault = true,
    ),
    var labels: List<Label> = listOf(),
    val createAt: LocalDateTime,
    var editedAt: LocalDateTime,
)
