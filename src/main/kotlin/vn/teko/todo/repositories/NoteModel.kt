package vn.teko.todo.repositories

import org.springframework.stereotype.Component
import vn.teko.todo.Services.Note
import vn.teko.todo.controllers.NoteDto

@Component
class NoteModel {
    fun mapToDto(note: Note) : NoteDto {
        var noteDto =  NoteDto(note.noteId, note.title, note.content)
        return noteDto;
    }

    fun mapToEntity(noteDto: NoteDto) : Note {
        var note = Note(noteDto.noteId, noteDto.title, noteDto.content)
        return  note;
    }
}