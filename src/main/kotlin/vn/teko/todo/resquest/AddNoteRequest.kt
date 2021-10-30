package vn.teko.todo.resquest

import org.springframework.beans.factory.annotation.Autowired
import vn.teko.todo.services.*
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AddNoteRequest(
    val id: Long,
    @Size(min = 8, max = 255)
    var title: String,
    @NotBlank
    var content: String,
    val colorId: Long = 3,
    val createAt: LocalDateTime = LocalDateTime.now(),
    var editedAt: LocalDateTime = LocalDateTime.now(),
)
