package vn.teko.todo.repositories

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import vn.teko.todo.services.Color
import vn.teko.todo.services.Note

@Table("colors")
data class ColorModel(
    @Id
    val id: Long,
    val name: String,
    val code: String,
    @Column("is_default")
    var isDefault: Boolean = false,
)

fun ColorModel.toColor(): Color = Color(
    id = this.id,
    name = this.name,
    code = this.code,
    isDefault = this.isDefault,
)

fun Color.toColorModel(): ColorModel = ColorModel(
    id = this.id,
    name = this.name,
    code = this.code,
    isDefault = this.isDefault,
)
