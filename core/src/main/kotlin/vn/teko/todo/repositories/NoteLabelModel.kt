package vn.teko.todo.repositories

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table("note_labels")
data class NoteLabelModel(
    @Id
    val id: Long = 0,
    @Column("note_id")
    private val noteId: Long,
    @Column("label_id")
    val labelId: Long,
)