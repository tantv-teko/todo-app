package vn.teko.todo.repositories

import com.fasterxml.jackson.annotation.JsonFormat
import vn.teko.todo.services.Note
import java.sql.Time
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "notes")
data class NoteModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String? = "",
    val content: String? = "",
    @Column(name = "create_at")
    val createAt: Date?,
    @Column(name = "edited_at")
    var editedAt: Date?,
)

fun NoteModel.toNote(): Note = Note(
    noteId = this.id,
    title =this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

fun Note.toNoteModel(): NoteModel = NoteModel(
    id = this.noteId,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

