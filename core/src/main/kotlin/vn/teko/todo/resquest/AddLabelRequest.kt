package vn.teko.todo.resquest

import vn.teko.todo.services.Label
import javax.validation.constraints.NotBlank

data class AddLabelRequest(
    @NotBlank
    val name: String,
)

fun  AddLabelRequest.toLabel() = Label(
    id = 0,
    name = this.name,
)