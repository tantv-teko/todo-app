package vn.teko.todo.repositories

import vn.teko.todo.services.Note
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "notes")
data class NoteModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String? = "",
    val content: String? = "",
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    val colorModel: ColorModel,
    @Column(name = "create_at")
    val createAt: LocalDateTime,
    @Column(name = "edited_at")
    var editedAt: LocalDateTime,
)

fun NoteModel.toNote(): Note = Note(
    id = this.id,
    title = this.title,
    content = this.content,
    color = this.colorModel.toColor(),
    createAt = this.createAt,
    editedAt = this.editedAt,
)

fun Note.toNoteModel(): NoteModel = NoteModel(
    id = this.id,
    title = this.title,
    content = this.content,
    colorModel = this.color.toColorModel(),
    createAt = this.createAt,
    editedAt = this.editedAt,
)