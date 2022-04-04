package vn.teko.todo.services

import java.io.Serializable

data class Color(
    val id: Long,
    val name: String,
    val code: String,
    var isDefault: Boolean = false,
): Serializable
