package vn.teko.todo.repositories

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("note_colors")
data class NoteColorModel(
    @Id
    val id: Long = 0,
    @Column("note_id")
    val noteId: Long,
    @Column("color_id")
    val colorId: Long,
)