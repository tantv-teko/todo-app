package vn.teko.todo.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LabelRepository: CrudRepository<LabelModel, Long> {
    @Query("SELECT l FROM LabelModel l WHERE l.id IN (SELECT nl.labelId FROM NoteLabelModel nl WHERE nl.noteId = :noteId)")
    fun findByNoteId(@Param("noteId")id: Long) : List<LabelModel>
}
