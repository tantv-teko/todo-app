package vn.teko.todo.repositories

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import vn.teko.todo.services.Color
import vn.teko.todo.services.Note
import java.time.LocalDateTime

@Table("notes")
data class NoteModel(
    @Id
    val id: Long,
    val title: String,
    val content: String,
    @Column("create_at")
    val createAt: LocalDateTime,
    @Column("edited_at")
    var editedAt: LocalDateTime,
)

fun NoteModel.toNote(color: Color): Note = Note(
    id = this.id,
    title = this.title,
    content = this.content,
    color = color,
    createAt = this.createAt,
    editedAt = this.editedAt,
)

fun Note.toNoteModel(): NoteModel = NoteModel(
    id = this.id,
    title = this.title,
    content = this.content,
    createAt = this.createAt,
    editedAt = this.editedAt,
)