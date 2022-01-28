package vn.teko.todo.handlers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import vn.teko.todo.controllers.toNoteDto
import vn.teko.todo.resquest.*
import vn.teko.todo.services.NoteService

@Component
class NoteHandler (
    private val noteService: NoteService,
) {

    suspend fun getNotes(
        req: ServerRequest,
    ): ServerResponse {
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(noteService.getNotes().map { it.toNoteDto() })
    }

    suspend fun getNote(
        req: ServerRequest,
    ): ServerResponse {
        val id = req.pathVariable("id").toLong()
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(noteService.getNote(id).toNoteDto())
    }

    suspend fun createNote(
        req: ServerRequest,
    ): ServerResponse {
        val request = req.awaitBody(AddNoteRequest::class)
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(noteService.createNote(request.toNote()).toNoteDto())
    }

    suspend fun updateNote(
        req: ServerRequest,
    ): ServerResponse {
        val id = req.pathVariable("id").toLong()
        val request = req.awaitBody(UpdateNoteRequest::class)
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(noteService.updateNote(id, request.toNote()).toNoteDto())
    }

    suspend fun deleteNote(
        req: ServerRequest,
    ): ServerResponse {
        val id = req.pathVariable("id").toLong()
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(noteService.deleteNote(id).toNoteDto())
    }


}