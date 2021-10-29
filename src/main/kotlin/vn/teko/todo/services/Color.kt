package vn.teko.todo.services

import javax.persistence.*

data class Color(
    val colorId: Long,
    val name: String,
    val code: String,
    var isDefault: Boolean = false,
)