package vn.teko.todo.services

import vn.teko.todo.controllers.NoteDto

interface NoteService {
    fun getNotes() : List<Note>
}
