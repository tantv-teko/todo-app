package vn.teko.todo.repositories

import com.fasterxml.jackson.annotation.JsonFormat
import vn.teko.todo.services.Note
import javax.persistence.*

@Entity
@Table(name = "note")
data class NoteModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var title: String? = "",
    var content: String? ="",

    @Column(name = "color_id ")
    var colorId: Long,

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "create_at")
    val createAt: String?,

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "edited_at")
    var editedAt: String?,

)

fun NoteModel.toNote(): Note = Note(
    noteId = this.id,
    title =this.title,
    content = this.content,
    colorId = this.colorId,
    createAt = this.createAt,
    editedAt = this.editedAt,
)


fun Note.toNoteModel(): NoteModel = NoteModel(
    id = this.noteId,
    title = this.title,
    content = this.content,
    colorId = this.colorId,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

