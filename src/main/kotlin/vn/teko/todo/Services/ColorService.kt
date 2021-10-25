package vn.teko.todo.services

interface ColorService {
    fun getColors() : List<Color>
    fun addColor(note: Color): Color
}
