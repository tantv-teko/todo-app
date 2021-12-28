package vn.teko.todo.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface NoteLabelRepository: CrudRepository<NoteLabelModel, Long> {
    fun deleteNoteLabelModelByNoteId(noteId: Long)
}