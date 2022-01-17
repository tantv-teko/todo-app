package vn.teko.todo.repositories


import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LabelRepository: CoroutineCrudRepository<LabelModel, Long> {
    @Query("SELECT * FROM labels l WHERE l.id IN (SELECT nl.label_id FROM note_labels nl WHERE nl.note_id = :noteId)")
    suspend fun findByNoteId(@Param("noteId")id: Long) : List<LabelModel>
}
