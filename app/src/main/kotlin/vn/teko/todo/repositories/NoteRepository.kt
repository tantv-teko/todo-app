package vn.teko.todo.repositories

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository: CoroutineCrudRepository<NoteModel, Long> {

}