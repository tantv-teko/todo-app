package vn.teko.todo.repositories


import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ColorRepository: CoroutineCrudRepository<ColorModel, Long> {
    @Query("SELECT * FROM colors c WHERE c.is_default = true")
    suspend fun findByDefault() : ColorModel
    @Query("SELECT * FROM colors c where  c.id IN (SELECT n.color_id FROM note_colors n WHERE n.note_id = :noteId)")
    suspend fun findByNoteId(@Param("noteId") noteId: Long) : ColorModel
}