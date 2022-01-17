package vn.teko.todo.services

interface ColorService {
    suspend fun getColors() : List<Color>
    suspend fun getColor(id: Long) : Color
}