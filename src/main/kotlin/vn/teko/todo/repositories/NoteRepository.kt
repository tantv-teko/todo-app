package vn.teko.todo.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import vn.teko.todo.Services.Note

@Repository
interface NoteRepository:CrudRepository<Note, Long> {
}