package vn.teko.todo.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NoteRepository: CrudRepository<NoteModel, Long> {
}
