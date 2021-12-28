package vn.teko.todo.repositories

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Color
import vn.teko.todo.services.Note
import java.time.LocalDateTime

internal class NoteModelTest {

    @Test
    fun toNote() {
        val noteModel = NoteModel(
            id = 0,
            title = "test",
            content = "content test",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val color = Color(
            id = 0,
            name = "test",
            code = "111",
        )
        val note = noteModel.toNote(color)
        assertThat(note.id).isEqualTo(noteModel.id)
        assertThat(note.title).isEqualTo(noteModel.title)
        assertThat(note.content).isEqualTo(noteModel.content)
        assertThat(note.color).isEqualTo(color)
        assertThat(note.createAt).isEqualTo(noteModel.createAt)
        assertThat(note.editedAt).isEqualTo(noteModel.editedAt)
    }

    @Test
    fun toNoteModel() {
        val note = Note(
            id = 0,
            title = "test",
            content = "content test",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val noteModel = note.toNoteModel()
        assertThat(noteModel.id).isEqualTo(note.id)
        assertThat(noteModel.title).isEqualTo(note.title)
        assertThat(noteModel.content).isEqualTo(note.content)
        assertThat(noteModel.createAt).isEqualTo(note.createAt)
        assertThat(noteModel.editedAt).isEqualTo(note.editedAt)
    }
}