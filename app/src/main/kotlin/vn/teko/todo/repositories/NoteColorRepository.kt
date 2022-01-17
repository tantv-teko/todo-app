package vn.teko.todo.repositories


import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NoteColorRepository: CoroutineCrudRepository<NoteColorModel, Long> {
    @Modifying
    @Query("DELETE FROM note_colors n WHERE n.note_id = :noteId")
    suspend fun deleteByNoteId(@Param("noteId")id: Long)
}