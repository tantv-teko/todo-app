package vn.teko.todo.services

data class Color(
    val id: Long,
    val name: String,
    val code: String,
    var isDefault: Boolean = false,
)
