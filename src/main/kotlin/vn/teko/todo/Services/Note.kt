package vn.teko.todo.Services

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Note(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val noteId: Long,
    var title: String? = "",
    var content: String? ="") {
    constructor(title: String?, content: String?) : this(1, title, content)
}
