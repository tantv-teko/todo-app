package vn.teko.todo.repositories


import vn.teko.todo.Services.Label
import javax.persistence.*

@Entity
@Table(name = "label")
data class LabelModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String?,
)

fun LabelModel.toLabel() : Label = Label(
    labelId = this.id,
    name = this.name,
)

fun Label.toLabelModel() : LabelModel = LabelModel(
    id = this.labelId,
    name = this.name,
)