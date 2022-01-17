package vn.teko.todo.repositories


import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteLabelRepository: CoroutineCrudRepository<NoteLabelModel, Long> {
    suspend fun deleteNoteLabelModelByNoteId(noteId: Long)
}