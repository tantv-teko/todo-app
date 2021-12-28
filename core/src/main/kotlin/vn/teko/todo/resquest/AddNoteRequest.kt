package vn.teko.todo.resquest

import vn.teko.todo.services.Color
import vn.teko.todo.services.Label
import vn.teko.todo.services.Note
import java.net.IDN
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AddNoteRequest(
    @Size(min = 8, max = 255)
    var title: String,
    @NotBlank
    var content: String,
    val colorId: Long?,
    val labelIds: List<Long> = listOf(),
    val createAt: LocalDateTime = LocalDateTime.now(),
)

fun AddNoteRequest.toNote() = Note(
    id = 0,
    title = this.title,
    content = this.content,
    color = if (colorId != null) Color(colorId, " ", " ") else Color(0, " ", " "),
    labels = this.labelIds.map { it -> Label(
        id = it,
        name = " ",
    ) },
    createAt = this.createAt,
    editedAt = this.createAt,
)