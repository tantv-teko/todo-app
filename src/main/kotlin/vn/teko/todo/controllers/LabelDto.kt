package vn.teko.todo.controllers

import vn.teko.todo.Services.Label

data class LabelDto(
    var id: Long,
    var name: String?
)

fun LabelDto.toLabel() : Label = Label(
    labelId = this.id,
    name = this.name,
)

fun Label.toLabelDto() : LabelDto = LabelDto(
    id = this.labelId,
    name = this.name,

)
