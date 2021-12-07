package vn.teko.todo.repositories

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface NoteColorRepository:  CrudRepository<NoteColorModel, Long> {
    @Modifying
    @Query("DELETE FROM NoteColorModel n WHERE n.noteId = :noteId")
    fun deleteByNoteId(@Param("noteId")id: Long)
}