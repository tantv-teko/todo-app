package vn.teko.todo.repositories

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import vn.teko.todo.services.Label

@Table("labels")
data class LabelModel(
    @Id
    val id: Long,
    var name: String,
)

fun LabelModel.toLabel() : Label = Label(
    id = this.id,
    name = this.name,
)

fun Label.toLabelModel() : LabelModel = LabelModel(
    id = this.id,
    name = this.name,
)