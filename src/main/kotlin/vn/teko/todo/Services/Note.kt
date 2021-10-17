package vn.teko.todo.Services

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Note(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val noteId: Long = 0,
    var title: String? = "",
    var content: String? =""
)