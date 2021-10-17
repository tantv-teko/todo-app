package vn.teko.todo.repositories

import org.springframework.data.repository.CrudRepository
import vn.teko.todo.Services.Note

interface NoteRepository:CrudRepository<Note, Long> {
}