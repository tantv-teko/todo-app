package vn.teko.todo.resquest

import vn.teko.todo.services.Note
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UpdateNoteRequest(
    val id: Long,
    @Size(min = 8, max = 255)
    var title: String? = "",
    @NotBlank
    var content: String? = "",
    val createAt: LocalDateTime = LocalDateTime.now(),
    var editedAt: LocalDateTime = LocalDateTime.now(),
)

fun UpdateNoteRequest.toNote() = Note(
    id = this.id,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)