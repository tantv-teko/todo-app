package vn.teko.todo.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ColorRepository: CrudRepository<ColorModel, Long> {
    @Query("SELECT c FROM ColorModel c where  c.id IN (SELECT n.colorId FROM NoteColorModel n WHERE n.noteId = :noteId)")
    fun findByNoteId(@Param("noteId") noteId: Long) : ColorModel
}