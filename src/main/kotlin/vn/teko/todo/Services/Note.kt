package vn.teko.todo.services

import com.fasterxml.jackson.annotation.JsonFormat
import javax.persistence.Column

data class Note(
    val noteId: Long,
    var title: String? = "",
    var content: String? ="",
    var colorId: Long,
    val createAt: String?,
    var editedAt: String?,

)
